package com.TripTrends;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

/**
* Created by manan on 10/31/2015.
*/
public class TripListActivity extends Activity {

    private static final String MEASUREMENT_ID_KEY = "measurement_id";
    private static final String TAG = "TripListActivity";

    private TripListAdapter mTripListAdapter;
    private static ArrayList<Review> sReviewList;
    private ListView mMeasurementListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_list);



        mMeasurementListView = (ListView) findViewById((R.id.measurements_list));
        sMeasurementList = new ArrayList<Measurement>();
        mTripListAdapter = new MeasurementAdapter(sMeasurementList, MeasurementListActivity.this);

        mMeasurementListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Measurement m = (Measurement) adapterView.getItemAtPosition(i);
                MeasurementStatus status = m.getStatus();
                switch (status) {
                    case DONE:
                        // if the measurement is done, go to the MeasurementStatus screen
                        Intent statusIntent = new Intent(MeasurementListActivity.this, MeasurementStatusActivity.class);
                        TextView selectedItem = (TextView) view.findViewById(R.id.measurement_id);
                        statusIntent.putExtra(MEASUREMENT_ID_KEY, selectedItem.getText().toString());
                        startActivity(statusIntent);
                        break;
                    case PROCESSING:
                        // if it's processing, update the status from the backend
                        new RequestStatusAsyncTask(getApplicationContext(), m.getId()).execute();
                        break;
                }
            }
        });

        // displays the popup context menu to either delete or resend measurement
        registerForContextMenu(mMeasurementListView);
        populateMeasurements();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Measurement measurement = sMeasurementList.get(info.position);
        // add context menu items
        menu.setHeaderTitle(measurement.getName());
        menu.add(0, v.getId(), 0, R.string.rename);
        menu.add(0, v.getId(), 0, R.string.delete);

        if (measurement.getStatus() != null) {
            if (measurement.getStatus().equals(MeasurementStatus.ERROR) ||
                    measurement.getStatus().equals(MeasurementStatus.NOT_SENT)) {
                // add the option to resend for measurements that failed for some reason or haven't been sent yet
                menu.add(0, v.getId(), 0, R.string.resend);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        String measurementId = sMeasurementList.get(info.position).getId();

        // handle click events on the context menu items
        if(item.getTitle().equals(getString(R.string.delete))){
            remove(measurementId);
        } else if(item.getTitle().equals(getString(R.string.resend))){
            resend(measurementId);
        } else if (item.getTitle().equals(getString(R.string.rename))){
            String name = sMeasurementList.get(info.position).getName();
            rename(measurementId, name);
        }
        return true;
    }

    /**
     * Creates a dialog that lets the user rename a measurement for his own convenience
     * @param id the local ID of the measurement to rename
     * @param oldName the current name of the measurement for displaying in the dialog's title
     */
    private void rename(final String id, String oldName) {
        AlertDialog.Builder renamePopup = new AlertDialog.Builder(this);
        renamePopup.setTitle(getString(R.string.rename)+ " - " + oldName);
        renamePopup.setMessage(R.string.enter_new_name);

        final EditText renameField = new EditText(this);
        renamePopup.setView(renameField);

        renamePopup.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Measurement measurement = mTripListAdapter.getMeasurementByID(id);
                measurement.setName(renameField.getText().toString());
                db.updateExistingMeasurement(measurement);

                showToast(getString(R.string.rename_success));

                return;
            }
        });
        renamePopup.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        renamePopup.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Removes a measurement from the database
     * @param id The local ID of the measurement to delete
     */
    private void remove(String id){
        // remove the measurement from the database
        Measurement measurement = mTripListAdapter.getMeasurementByID(id);
        mTripListAdapter.remove(measurement);
        db.deleteMeasurement(id);

        // Show a success message
        showToast(getString(R.string.delete_success));
    }

    /**
     * Resends a measurement to the backend
     * @param id The local ID of the measurement to resend
     */
    private void resend(String id){
        new UploadMeasurementAsyncTask(getApplicationContext(), id).execute();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * Gets all measurements from the database and puts them in the list
     */
    private void populateMeasurements() {
        try {
            ArrayList<Measurement> measurements = (ArrayList) db.getAllMeasurements();
            for (Measurement measurement : measurements) {
                sMeasurementList.add(measurement);

                measurement.setMeasurementChangeListener(
                        new Measurement.MeasurementChangeListener() {
                            @Override
                            public void onMeasurementChangeListener() {
                                // make sure to update the list every time e measurement is changed
                                mTripListAdapter.notifyDataSetChanged();
                            }
                        });

                if (measurement.getStatus() == MeasurementStatus.PROCESSING){
                    // auto-refresh processing measurements
                    new RequestStatusAsyncTask(getApplicationContext(), measurement.getId()).execute();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        mMeasurementListView.invalidate();
        mMeasurementListView.setAdapter(mTripListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refreshable_list, menu);
        return true;
    }

    private void refreshStatusOfAllProcessingMeasurements() {
        for (Measurement measurement : sMeasurementList) {
            MeasurementStatus measurementStatus = measurement.getStatus();
            if (measurementStatus == MeasurementStatus.PROCESSING) {
                new RequestStatusAsyncTask(getApplicationContext(), measurement.getId()).execute();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.action_refresh_all:
                refreshStatusOfAllProcessingMeasurements();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}

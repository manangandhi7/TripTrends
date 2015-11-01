package com.TripTrends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
* Created by manan on 10/31/2015
*/
public class TripListActivity extends Activity {

    private static final String REVIEWDATA_ID_KEY = "review_id";
    private static final String TAG = "TripListActivity";

    private TripListAdapter mTripListAdapter;
    private static ArrayList<ReviewData> mReviewList;
    private ListView mMeasurementListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_list);

        mMeasurementListView = (ListView) findViewById(R.id.trip_list);
        mReviewList = getReviewData();
        mTripListAdapter = new TripListAdapter(mReviewList, TripListActivity.this);

        mMeasurementListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReviewData m = (ReviewData) adapterView.getItemAtPosition(i);

                // if the measurement is done, go to the MeasurementStatus screen
                Intent statusIntent = new Intent(TripListActivity.this, ViewMapActivity.class);
                TextView selectedItem = (TextView) view.findViewById(R.id.review_id);
                statusIntent.putExtra(REVIEWDATA_ID_KEY, selectedItem.getText().toString());
                startActivity(statusIntent);
            }
        });

        // displays the popup context menu to either delete or resend measurement
        //registerForContextMenu(mMeasurementListView);
        populateMeasurements();
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        ReviewData measurement = mReviewList.get(info.position);
//        // add context menu items
//        menu.setHeaderTitle(measurement.getName());
//        menu.add(0, v.getId(), 0, R.string.rename);
//        menu.add(0, v.getId(), 0, R.string.delete);
//
//        if (measurement.getStatus() != null) {
//            if (measurement.getStatus().equals(MeasurementStatus.ERROR) ||
//                    measurement.getStatus().equals(MeasurementStatus.NOT_SENT)) {
//                // add the option to resend for measurements that failed for some reason or haven't been sent yet
//                menu.add(0, v.getId(), 0, R.string.resend);
//            }
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//        //int measurementId = mReviewList.get(info.position).getId();
//
//        // handle click events on the context menu items
////        if(item.getTitle().equals(getString(R.string.delete))){
////            remove(measurementId);
////        }
//        return true;
//    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
            ArrayList<ReviewData> measurements = getReviewData();
            for (ReviewData measurement : measurements) {
                mReviewList.add(measurement);               //remove later
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        mMeasurementListView.invalidate();
        mMeasurementListView.setAdapter(mTripListAdapter);
    }

    private ArrayList<ReviewData> getReviewData(){
        ArrayList<ReviewData> arrayList = new ArrayList<ReviewData>();

        ReviewData rev1 = new ReviewData();
        ReviewData rev2 = new ReviewData();
        ReviewData rev3 = new ReviewData();
        ReviewData rev4 = new ReviewData();
        ReviewData rev5 = new ReviewData();

        rev1.setCity("Berlin");
        rev1.setCategory(1);
        rev1.setNumPeople(1);
        rev1.setComment("Wildpark is nice place to have picnic!");
        rev1.setNumPeople(4);
        rev1.setId(1);
        rev1.setStars(5);
        rev1.setDistance(95);
        rev1.ratedBy = 61;
        rev1.imageID = R.drawable.front_cam;
        rev1.setDuration(3);

        rev2.setCity("Berlin");
        rev2.setCategory(2);
        rev2.setNumPeople(3);
        rev2.setComment("Be sure to try sushi!");
        rev1.setNumPeople(3);
        rev1.setId(2);
        rev1.setStars(4);
        rev1.ratedBy = 54;
        rev1.setDistance(95);
        rev1.imageID = R.drawable.map1;
        rev2.setDuration(4);

        rev3.setCity("Berlin");
        rev3.setCategory(2);
        rev3.setNumPeople(3);
        rev3.setComment("Make sure to go to visit Louvr meusum so early!");
        rev1.setNumPeople(4);
        rev1.setId(3);
        rev1.setStars(4);
        rev1.setDistance(95);
        rev1.imageID = R.drawable.map1;
        rev3.setDuration(3);

        rev4.setCity("Berlin");
        rev4.setCategory(2);
        rev4.setNumPeople(3);
        rev4.setComment("Want no sound pollutio? This is the right city to visit!");
        rev4.setDuration(7);

        rev5.setCity("Berlin");
        rev5.setCategory(3);
        rev5.setNumPeople(3);
        rev5.setComment("I liked watching Cricket match in the main stadium!");
        rev5.setDuration(7);

        arrayList.add(rev1);
        arrayList.add(rev2);
        arrayList.add(rev3);
        arrayList.add(rev4);
        arrayList.add(rev5);

        return arrayList;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.refreshable_list, menu);
//        return true;
//    }
//
//    private void refreshStatusOfAllProcessingMeasurements() {
//        for (ReviewData measurement : sMeasurementList) {
//            MeasurementStatus measurementStatus = measurement.getStatus();
//            if (measurementStatus == MeasurementStatus.PROCESSING) {
//                new RequestStatusAsyncTask(getApplicationContext(), measurement.getId()).execute();
//            }
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        switch(menuItem.getItemId()) {
//            case R.id.action_refresh_all:
//                refreshStatusOfAllProcessingMeasurements();
//                return true;
//            default:
//                return super.onOptionsItemSelected(menuItem);
//        }
//    }
}

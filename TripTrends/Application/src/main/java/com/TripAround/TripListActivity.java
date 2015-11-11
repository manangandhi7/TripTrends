package com.TripAround;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
    private ListView mReviewDataListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_list);
        this.setTitle("Berlin");
        this.getActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF' Bold=\"true\" size = \"20\"> Berlin </font>"));

        mReviewDataListView = (ListView) findViewById(R.id.trip_list);
        mReviewList = new ArrayList<ReviewData>();
        mTripListAdapter = new TripListAdapter(mReviewList, TripListActivity.this);

        mReviewDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        registerForContextMenu(mReviewDataListView);
        populateMeasurements();
    }

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

        mReviewDataListView.invalidate();
        mReviewDataListView.setAdapter(mTripListAdapter);
    }

    private ArrayList<ReviewData> getReviewData(){
        ArrayList<ReviewData> arrayList = new ArrayList<ReviewData>();

        ReviewData rev1 = new ReviewData();
        ReviewData rev2 = new ReviewData();
        ReviewData rev3 = new ReviewData();
        ReviewData rev4 = new ReviewData();
        ReviewData rev5 = new ReviewData();

        rev1.setCity("Manan");
        rev1.category = "Touristic";
        rev1.setComment("Wildpark is nice place to have picnic!");
        rev1.setNumPeople(4);
        rev1.setId(1);
        rev1.setStars(5);
        rev1.setDistance(125);
        rev1.ratedBy = 61;
        rev1.imageID = R.drawable.rsz_map1;
        rev1.setDuration(3);

        rev2.setCity("Kirsten");
        rev2.category = "Touristic";
        rev2.setComment("Be sure to try sushi!");
        rev2.setNumPeople(3);
        rev2.setId(2);
        rev2.setStars(4);
        rev2.ratedBy = 54;
        rev2.setDistance(136);
        rev2.imageID = R.drawable.rsz_map2;
        rev2.setDuration(4);

        rev3.setCity("Majid");
        rev3.category = "Touristic";
        rev3.setNumPeople(3);
        rev3.setComment("Make sure to go to visit Louvr meusum so early!");
        rev3.setId(3);
        rev3.setStars(4);
        rev3.setDistance(95);
        rev3.ratedBy = 38;
        rev3.imageID = R.drawable.rsz_map3;
        rev3.setDuration(3);

        rev4.setCity("Andy");
        rev4.category = "Touristic";
        rev4.setNumPeople(3);
        rev4.setComment("Want no sound pollutio? This is the right city to visit!");
        rev4.setDuration(2);
        rev4.setId(4);
        rev4.setStars(4);
        rev4.setDistance(79);
        rev4.imageID = R.drawable.rsz_map4;
        rev4.ratedBy = 15;

        rev5.setCity("Matt");
        rev5.category = "Touristic";
        rev5.setComment("I liked watching Cricket match in the main stadium!");
        rev5.setDuration(5);
        rev5.setNumPeople(5);
        rev5.setId(5);
        rev5.setStars(3);
        rev5.setDistance(105);
        rev4.imageID = R.drawable.rsz_map4;
        rev4.ratedBy = 12;

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

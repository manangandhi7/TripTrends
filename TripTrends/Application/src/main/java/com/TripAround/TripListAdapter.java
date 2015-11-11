package com.TripAround;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manan on 10/31/2015.
 */
    public class TripListAdapter extends ArrayAdapter<ReviewData> {
        private static final String TAG = "TripListAdapter";

        private static List<ReviewData> mReviewDataList;
        private Context mContext;

        public TripListAdapter(List<ReviewData> reviewDataList, Context context) {
            super(context, R.layout.trips_list, reviewDataList);
            this.mReviewDataList = reviewDataList;
            this.mContext = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            DataHolder holder = new DataHolder();

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.trip_list_item, null);
                     if (v == null){
                         throw new  RuntimeException("Fuck!");
                     }
                // fill the layout with the right values
                //v.setBackgroundColor(Color.GRAY);

                TextView idView = (TextView) v.findViewById(R.id.review_id);
                TextView cityView = (TextView) v.findViewById(R.id.city_name);
                TextView starsView = (TextView) v.findViewById(R.id.stars_string);
                TextView daysView = (TextView) v.findViewById(R.id.num_days);
                TextView categoryView = (TextView) v.findViewById(R.id.categoty);
                TextView kmsView = (TextView) v.findViewById(R.id.kilometers);
                ImageView starsImage = (ImageView) v.findViewById(R.id.star1);
                ImageView routeView = (ImageView) v.findViewById(R.id.route_image);



                holder.reviewId = idView;
                holder.cityName = cityView;
                holder.starsRating = starsView;
                holder.numDays = daysView;
                holder.category = categoryView;
                holder.kms = kmsView;
                holder.starImage = starsImage;
                holder.routeImage = routeView;

                v.setTag(holder);
            }
            else
                holder = (DataHolder) v.getTag();

            ReviewData m = mReviewDataList.get(position);
            //SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat toFormat = new SimpleDateFormat("HH:mm dd.MM.yy");
            //String sDate = m.getStartDate();
//            try {
//                sDate = toFormat.format(fromFormat.parseObject(sDate));
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }

            holder.reviewId.setText(Integer.toString(m.getId()));
            holder.cityName.setText(m.getCity());
            holder.starsRating.setText(Integer.toString(m.getStars()));
            holder.numDays.setText(Integer.toString(m.getDuration()) + " Days");
            holder.category.setText(m.category);
            holder.kms.setText(Integer.toString(m.getDistance()) + "km");
            holder.starImage.setImageResource(R.drawable.star);
            holder.routeImage.setImageResource(m.imageID);

            return v;
        }

//        public static ReviewData getReviewItemByID(String ID){
//            if(mReviewDataList != null){
//                for(ReviewData m : mReviewDataList){
//                    if(m != null && Integer.toString(m.getId()).equals(ID)){
//                        return m;
//                    }
//                }
//            }
//            return null;
//        }

        class DataHolder {
            public TextView reviewId;
            public TextView cityName;
            public TextView starsRating;
            public TextView numDays;
            public TextView category;
            public TextView kms;
            public ImageView starImage;
            public ImageView routeImage;
        }
    }


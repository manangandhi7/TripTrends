package com.TripTrends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText)findViewById(R.id.city_name);
        editText.setText(getString(R.string.enter_city), TextView.BufferType.EDITABLE);
        editText.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_name:
                EditText editText = (EditText) findViewById(R.id.city_name);
                if (editText.getText().toString().equals(getString(R.string.enter_city))) {
                    editText.setText("");
                }
                break;
            case R.id.class1:
                TextView textView = (TextView)findViewById(R.id.class1);
                ColorDrawable cd = (ColorDrawable) textView.getBackground();
                int colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.class2:
                textView = (TextView)findViewById(R.id.class2);
                cd = (ColorDrawable) textView.getBackground();
                colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.class3:
                textView = (TextView)findViewById(R.id.class3);
                cd = (ColorDrawable) textView.getBackground();
                colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.class4:
                textView = (TextView)findViewById(R.id.class4);
                cd = (ColorDrawable) textView.getBackground();
                colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.class5:
                textView = (TextView)findViewById(R.id.class5);
                cd = (ColorDrawable) textView.getBackground();
                colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.class6:
                textView = (TextView)findViewById(R.id.class6);
                cd = (ColorDrawable) textView.getBackground();
                colorCode = cd.getColor();
                if (colorCode == getResources().getColor(R.color.holo_orange_light)) {
                    textView.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
                } else {
                    textView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
                }
                break;
            case R.id.search_button:
                Intent toListIntent = new Intent(MainActivity.this, TripListActivity.class);
                startActivity(toListIntent);
                break;
        }
    }
}
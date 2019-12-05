package com.example.hungrytogetherandroidapplication.new_order_portal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.hungrytogetherandroidapplication.R;

public class select_pickup_location extends NewOrderActivity {

    Button blk_57;
    Button library;
    Button mrt;
    public static final String LOC_KEY = "LOC_KEY";
    public static final String SAVE_LOCATION = "SAVE_LOCATION"; //to save variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pickup_location);


        //instantiating all buttons
        blk_57 = findViewById(R.id.blk_57);
        library = findViewById(R.id.library);
        mrt = findViewById(R.id.mrt);

        //set up the dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        //setting onClick for blk 57
        blk_57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_location = new Intent(select_pickup_location.this, NewOrderActivity.class);
                String location_choice = blk_57.getText().toString();
                intent_location.putExtra(LOC_KEY, location_choice);

                SharedPreferences saved_location_state = getSharedPreferences("LOCATION", 0); //0 == first slot
                SharedPreferences.Editor editor = saved_location_state.edit();
                editor.putString(SAVE_LOCATION,location_choice); //save location choice using key SAVE_LOCATION
                editor.commit(); //so if app crashes, location choice is still saved

                startActivity(intent_location);
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_location = new Intent(select_pickup_location.this, NewOrderActivity.class);
                String location_choice = library.getText().toString();;
                intent_location.putExtra(LOC_KEY, location_choice);

                SharedPreferences saved_location_state = getSharedPreferences("LOCATION", 0); //0 == first slot
                SharedPreferences.Editor editor = saved_location_state.edit();
                editor.putString(SAVE_LOCATION,location_choice); //save location choice using key SAVE_LOCATION
                editor.commit(); //so if app crashes, location choice is still saved

                startActivity(intent_location);


            }
        });

        mrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_location = new Intent(select_pickup_location.this, NewOrderActivity.class);
                String location_choice = mrt.getText().toString();
                //intent_location.putExtra(LOC_KEY, location_choice);

                //save location_choice
                SharedPreferences saved_location_state = getSharedPreferences("LOCATION", 0); //0 == first slot
                SharedPreferences.Editor editor = saved_location_state.edit();
                editor.putString(SAVE_LOCATION,location_choice); //save location choice using key SAVE_LOCATION
                editor.commit(); //so if app crashes, location choice is still saved

                startActivity(intent_location);


            }
        });

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putString("location_choice", location_choice ); //saving the string
//
//
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState){
//        super.onRestoreInstanceState(savedInstanceState);
//
//        if (savedInstanceState != null){
//            String location_choice = savedInstanceState.getString("location_choice");
//        }
//
//    }
}

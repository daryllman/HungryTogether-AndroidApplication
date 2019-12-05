package com.example.hungrytogetherandroidapplication.new_order_portal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.hungrytogetherandroidapplication.R;

public class select_restaurant extends NewOrderActivity {

    //initiating all required variables
    Button macs;
    public final static String RES_KEY = "RES_KEY";
    public final static String SAVE_RESTAURANT = "SAVE_RESTAURANT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restaurant);

        //instantiating all buttons that we need
        macs = findViewById(R.id.macs);

        //setting the dimensions for the screen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));

        //==== SET METHODS FOR ALL BUTTONS ======

        //update button content on prev screen to show macs
        macs.setOnClickListener(new View.OnClickListener() {
            //abstract method implementation -- control i
            @Override
            public void onClick(View v) {

                Intent intent_macs = new Intent(select_restaurant.this, NewOrderActivity.class);
                String restaurant_choice = macs.getText().toString();

                SharedPreferences saved_restaurant_state = getSharedPreferences("RESTAURANT", 0); //0 == first slot
                SharedPreferences.Editor editor = saved_restaurant_state.edit();
                editor.putString(SAVE_RESTAURANT,restaurant_choice); //save location choice using key SAVE_LOCATION
                editor.commit(); //so if app crashes, location choice is still saved

                startActivity(intent_macs);
            }
        });

    }
}

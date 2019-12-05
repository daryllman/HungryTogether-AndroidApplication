package com.example.hungrytogetherandroidapplication.new_order_portal;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.hungrytogetherandroidapplication.R;


public class NewOrderActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    //instantiate all buttons that we need
    Button select_restaurant_button;
    Button select_pickup_location_button;
    Button select_time_limit_button;
    Button select_slots_available;
    Button confirm;

    //variables to be pushed to firebase when confirm is pressed
    String get_restaurant_choice;
    String get_location_choice;
    String get_time_choice;
    String get_slots_choice;


    @Override
    public void onTimeSet (TimePicker view,int hourOfDay, int minute){
        if (minute < 10){
            select_time_limit_button.setText(hourOfDay + " : 0" + minute);
            get_time_choice = select_time_limit_button.getText().toString();
        }

        else {
            select_time_limit_button.setText(hourOfDay + " : " + minute);
            get_time_choice = select_time_limit_button.getText().toString();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //file that stores layout
        setContentView(R.layout.activity_new_order);

        //you can't instantiate variables before storing layout
        select_restaurant_button = findViewById(R.id.select_restaurant_button);
        select_pickup_location_button = findViewById(R.id.select_pickup_location_button);
        select_time_limit_button = findViewById(R.id.select_time_limit_button);
        select_slots_available = findViewById(R.id.select_slots_available_button);
        confirm = findViewById(R.id.confirm);

        select_slots_available.setText("slots available...");


        //===== THIS PART IS TO TRANSFER DATA FROM ONE SCREEN TO ANOTHER ===== //
        Intent subToNewOrderActivity = getIntent();

        //getting shared preferences for location
        SharedPreferences saved_location_state = getSharedPreferences("LOCATION", 0);
        get_location_choice = saved_location_state.getString("SAVE_LOCATION", "select location...");

        //getting shared preferences for restaurant
        SharedPreferences saved_restaurant_state = getSharedPreferences("RESTAURANT", 0);
        get_restaurant_choice = saved_restaurant_state.getString("SAVE_RESTAURANT", "select restaurant...");

        //getting shared preferences for slots available
        SharedPreferences saved_slots_state = getSharedPreferences("SLOTS", 0);
        get_slots_choice = saved_slots_state.getString("SAVE_SLOTS", "slots available...");

        select_restaurant_button.setText(get_restaurant_choice);
        select_pickup_location_button.setText(get_location_choice);
        select_slots_available.setText(get_slots_choice);

        //==== SET CHANGE SCREEN METHOD FOR ALL BUTTONS =====//
        select_restaurant_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), select_restaurant.class);
                startActivityForResult(myIntent, 0);
            }
        });

        select_pickup_location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), select_pickup_location.class);
                startActivityForResult(myIntent, 0);
            }
        });

        select_time_limit_button.setOnClickListener(new View.OnClickListener() {
            //abstract method implementation -- control i
            @Override
            public void onClick(View v) {

                DialogFragment selectTime = new select_time_limit();
                selectTime.show(getSupportFragmentManager(), "selectTime");
            }
        });

        select_slots_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), com.example.hungrytogetherandroidapplication.new_order_portal.select_slots_available.class);
                startActivityForResult(myIntent, 0);            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (get_restaurant_choice == null || get_location_choice == null ||
                        get_time_choice == null || get_slots_choice == null){
                    Toast.makeText(NewOrderActivity.this, "please fill everything up first!", Toast.LENGTH_LONG).show();

                }

                else {
                    Toast.makeText(NewOrderActivity.this, "order confirmed", Toast.LENGTH_LONG).show();
                    Log.i("glenda", get_restaurant_choice);
                    Log.i("glenda", get_location_choice);
                    Log.i("glenda", get_time_choice);
                    Log.i("glenda", get_slots_choice);
                }
            }
        });

    }


}

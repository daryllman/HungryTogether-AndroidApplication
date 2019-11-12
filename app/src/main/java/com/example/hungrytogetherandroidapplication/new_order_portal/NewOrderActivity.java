package com.example.hungrytogetherandroidapplication.new_order_portal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hungrytogetherandroidapplication.R;

public class NewOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //file that stores layout
        setContentView(R.layout.activity_new_order);

        //instantiate all buttons that we need
        Button select_restaurant = findViewById(R.id.select_restaurant);
        Button select_pickup_location = findViewById(R.id.select_pickup_location);
        Button select_time_limit = findViewById(R.id.select_time_limit);

        //set change screen method for all buttons
        select_restaurant.setOnClickListener(new View.OnClickListener(){
            //abstract method implementation -- control i
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), select_restaurant.class);
                startActivityForResult(myIntent, 0);
            }
        });

        select_pickup_location.setOnClickListener(new View.OnClickListener(){
            //abstract method implementation -- control i
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), select_pickup_location.class);
                startActivityForResult(myIntent, 0);
            }
        });

        select_time_limit.setOnClickListener(new View.OnClickListener(){
            //abstract method implementation -- control i
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), select_time_limit.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}

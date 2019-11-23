package com.example.hungrytogetherandroidapplication.open_orders_portal;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytogetherandroidapplication.R;

import java.util.ArrayList;
import java.util.List;

public class OpenOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OpenOrderAdapter adapter;

    List<OpenOrderItem> openOrderItemList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);


        recyclerView = (RecyclerView) findViewById(R.id.open_orders_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //by default is vertical layout

        openOrderItemList = new ArrayList<>();

        //adding some items to our list
        openOrderItemList.add(
                new OpenOrderItem(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.macdonalds_background));

        openOrderItemList.add(
                new OpenOrderItem(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.macdonalds_background));

        openOrderItemList.add(
                new OpenOrderItem(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.macdonalds_background));

        adapter = new OpenOrderAdapter(this, openOrderItemList);
        recyclerView.setAdapter(adapter);


            /*
            ImageView restaurantImagesView = (ImageView) convertView.findViewById(R.id.restaurant_image);
            TextView restaurantNameView = (TextView)convertView.findViewById(R.id.restaurant_name);
            TextView foodCaptainNameView = (TextView)convertView.findViewById(R.id.food_captain_name);
            TextView pickUpPointNameView = (TextView)convertView.findViewById(R.id.pick_up_point_name);
            TextView timeLeftNameView = (TextView)convertView.findViewById(R.id.time_left_name);
            TextView slotsLeftNameView = (TextView)convertView.findViewById(R.id.slots_left_name);

            restaurantImagesView.setImageResource(restaurantImages[position]);
            restaurantNameView.setText(restaurantName[position]);
            foodCaptainNameView.setText(foodCaptainName[position]);
            pickUpPointNameView.setText(pickUpPointName[position]);
            timeLeftNameView.setText(timeLeftName[position]);
            slotsLeftNameView.setText(slotsLeftName[position]);

             */


        }
    }


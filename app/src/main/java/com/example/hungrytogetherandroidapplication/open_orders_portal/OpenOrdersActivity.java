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

        //Add open orders to list!
        openOrderItemList.add(
                new OpenOrderItem(
                        R.drawable.macdonalds_background,
                        "MacDonald's ",
                        "Daryll One",
                        "Blk 59",
                        22,
                        2));


        adapter = new OpenOrderAdapter(this, openOrderItemList);
        recyclerView.setAdapter(adapter);
        }
    }


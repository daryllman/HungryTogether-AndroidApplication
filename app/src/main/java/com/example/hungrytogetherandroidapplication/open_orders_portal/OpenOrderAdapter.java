package com.example.hungrytogetherandroidapplication.open_orders_portal;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.login_portal.AccountDetailsActivity;

import java.util.List;

/*
need to have:
1) RecyclerView.Adapter
2) RecyclerView.ViewHolder
 */

public class OpenOrderAdapter extends RecyclerView.Adapter<OpenOrderAdapter.OpenOrderViewHolder>{

    private Context mContext;
    private List<OpenOrderItem> open_orders_list;

    public OpenOrderAdapter(Context mContext, List<OpenOrderItem> open_orders_list) {
        this.mContext = mContext;
        this.open_orders_list = open_orders_list;
    }

    @Override
    public OpenOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //returns instance of OpenOrderViewHolder: UI elements
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.open_order_item, null);
        OpenOrderViewHolder holder = new OpenOrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OpenOrderViewHolder holder, int position) { //Binds data to OpenOrderViewHolder(UI element)
        final OpenOrderItem item = open_orders_list.get(position);

        holder.restaurantImage.setImageResource(item.getRestaurant_image());
        holder.restaurantName.setText(item.getRestaurant_name());
        holder.foodCaptainName.setText(item.getFood_captain_name());
        holder.pickUpPointName.setText(String.valueOf(item.getPick_up_point_name()));
        holder.timeLeftNum.setText(String.valueOf(item.getTime_left_num()));
        holder.slotsLeftNum.setText(String.valueOf(item.getSlots_left_num()));


        holder.openOrderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String foodCaptainName = "the food captain is" + item.getFood_captain_name();

                Intent intent = new Intent(v.getContext(), AccountDetailsActivity.class);
                intent.putExtra("iFoodCaptainName", foodCaptainName);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //returns size of the list
        return open_orders_list.size();
    }

    class OpenOrderViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImage;
        TextView restaurantName, foodCaptainName, pickUpPointName, timeLeftNum, slotsLeftNum;
        Button openOrderButton;

        public OpenOrderViewHolder(View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            foodCaptainName = itemView.findViewById(R.id.food_captain_name);
            pickUpPointName = itemView.findViewById(R.id.pick_up_name);
            timeLeftNum = itemView.findViewById(R.id.time_left_num);
            slotsLeftNum = itemView.findViewById(R.id.slots_left_num);
            openOrderButton = itemView.findViewById(R.id.view_open_order_button);
        }
    }
}

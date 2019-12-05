package com.example.hungrytogetherandroidapplication.my_orders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.open_orders.OpenOrderItem;

import java.util.List;


public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyOrdersViewHolder>{

    private Context mContext;
    private List<MyOrderItem> open_orders_list;
    private Activity mActivity;

    public MyOrdersAdapter(Context mContext, List<MyOrderItem> open_orders_list, Activity mActivity) {
        this.mContext = mContext;
        this.open_orders_list = open_orders_list;
        this.mActivity = mActivity;
    }

    @Override
    public MyOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //returns instance of OpenOrderViewHolder: UI elements
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_order_item, null);
        MyOrdersViewHolder holder = new MyOrdersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyOrdersViewHolder holder, int position) { //Binds data to OpenOrderViewHolder(UI element)
        final MyOrderItem item = open_orders_list.get(position);

        holder.restaurantName.setText(item.getRestaurant_name());
        holder.captainName.setText(item.getCaptain_name());
        holder.dateTimeDeadline.setText(item.getDatetimedeadline());
        holder.pickupLocation.setText(item.getPickup_location());
        holder.mealLabel.setText(item.getMeal_label());
        holder.mealCost.setText(item.getMeal_cost());
        holder.captainFee.setText(item.getCaptain_fee());
        holder.totalCost.setText(item.getTotal_cost());
        holder.payMyOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mActivity);
                View mView = mActivity.getLayoutInflater().inflate(R.layout.payment_dialog, null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        switch (item.getProgress_state()){
            case "0":
                holder.progressBarStatusImage.setImageResource(R.drawable.progress_bar_state0);
                break;
            case "1":
                holder.progressBarStatusImage.setImageResource(R.drawable.progress_bar_state1);
                break;
            case "2":
                holder.progressBarStatusImage.setImageResource(R.drawable.progress_bar_state2);
                break;
            case "3":
                holder.progressBarStatusImage.setImageResource(R.drawable.progress_bar_state3);
                break;
            default:

        }


    }

    @Override
    public int getItemCount() { //returns size of the list
        return open_orders_list.size();
    }

    class MyOrdersViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName, captainName, dateTimeDeadline,pickupLocation, mealLabel, mealCost, captainFee, totalCost;
        Button payMyOrderButton;
        ImageView progressBarStatusImage;

        public MyOrdersViewHolder(View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurant_name);
            captainName = itemView.findViewById(R.id.restaurant_name);
            dateTimeDeadline = itemView.findViewById(R.id.datetimedeadline_time);
            mealLabel = itemView.findViewById(R.id.meal_label);
            mealCost = itemView.findViewById(R.id.meal_cost);
            captainFee = itemView.findViewById(R.id.captain_fee);
            totalCost = itemView.findViewById(R.id.total_cost);
            payMyOrderButton = itemView.findViewById(R.id.pay_my_order_button);
            progressBarStatusImage = itemView.findViewById(R.id.progress_bar_status_image);
            pickupLocation = itemView.findViewById(R.id.pick_up_location);
        }
    }
}

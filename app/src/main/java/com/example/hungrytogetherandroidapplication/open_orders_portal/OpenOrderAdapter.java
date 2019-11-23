package com.example.hungrytogetherandroidapplication.open_orders_portal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytogetherandroidapplication.R;

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
        OpenOrderItem item = open_orders_list.get(position);

        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDesc.setText(item.getShortdesc());
        holder.textViewRating.setText(String.valueOf(item.getRating()));
        holder.textViewPrice.setText(String.valueOf(item.getPrice()));

        holder.imageView.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() { //returns size of the list
        return open_orders_list.size();
    }

    class OpenOrderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice;

        public OpenOrderViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}

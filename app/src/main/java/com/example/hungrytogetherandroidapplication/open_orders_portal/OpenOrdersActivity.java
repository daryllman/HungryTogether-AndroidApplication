package com.example.hungrytogetherandroidapplication.open_orders_portal;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hungrytogetherandroidapplication.R;

public class OpenOrdersActivity extends AppCompatActivity {

    int[] restaurantImages = {R.drawable.macdonalds_background, R.drawable.macdonalds_background};
    String[] restaurantName = { "Macdonald's", "Macdonald's"};
    String[] foodCaptainName = { "Daryll", "Daryll2"};
    String[] pickUpPointName = { "Blk57", "Blk59"};
    String[] timeLeftName = { "22mins", "11mins"};
    String[] slotsLeftName = { "2","3"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        ListView openOrdersListView = (ListView) findViewById(R.id.open_orders_list);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        openOrdersListView.setAdapter(customAdaptor);

    }




    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return foodCaptainName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.open_order_item, null);

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

            return convertView;
        }
    }




}

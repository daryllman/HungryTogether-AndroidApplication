package com.example.hungrytogetherandroidapplication.open_orders_portal;

import android.content.res.Resources;
import android.net.Uri;

public class OpenOrderItem {


    private int restaurant_image;
    private String restaurant_name;
    private String food_captain_name;
    private String pick_up_point_name;
    private int time_left_num;
    private int slots_left_num;

    public OpenOrderItem(int restaurant_image, String restaurant_name, String food_captain_name, String pick_up_point_name, int time_left_num, int slots_left_num) {
        this.restaurant_image = restaurant_image;
        this.restaurant_name = restaurant_name;
        this.food_captain_name = food_captain_name;
        this.pick_up_point_name = pick_up_point_name;
        this.time_left_num = time_left_num;
        this.slots_left_num = slots_left_num;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getFood_captain_name() {
        return food_captain_name;
    }

    public String getPick_up_point_name() {
        return pick_up_point_name;
    }

    public int getTime_left_num() {
        return time_left_num;
    }

    public int getSlots_left_num() {
        return slots_left_num;
    }
}

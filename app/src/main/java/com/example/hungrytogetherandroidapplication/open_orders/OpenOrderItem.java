package com.example.hungrytogetherandroidapplication.open_orders;


import android.net.Uri;

import java.net.URI;
import java.net.URL;

public class OpenOrderItem {
    private String restaurant_image;
    private String restaurant_name;
    private String captain_name;
    private String pickup_location;
    private String datetimedeadline;
    private String slots_left;
    private String order_id;

    public OpenOrderItem() {
        // empty constructor
    }

    public OpenOrderItem(String restaurant_image, String restaurant_name, String captain_name, String pickup_location, String datetimedeadline, String slots_left, String order_id) {
        this.restaurant_image = restaurant_image;
        this.restaurant_name = restaurant_name;
        this.captain_name = captain_name;
        this.pickup_location = pickup_location;
        this.datetimedeadline = datetimedeadline;
        this.slots_left = slots_left;
        this.order_id = order_id;
    }

    //NOTE!!
    // For some reason, firebase only recognises if your getter functions are named in this very specific way - in accordance to the names of your variables too
    // technically the name does not matter as you have to determine which getter func to use
    // 1. Name of variables must be exactly the same as the ones in firebase
    // 2. Name of getter function must be in this specific format - according to variable name
    // 3. Last tip: Just use String. ie make sure we convert and send as String to firebase; easier to retrive as String too
         // If you use something else, theres some deserialising problem that you have to fix due to the value types of firebase data type
    public String getRestaurant_image() {
        return restaurant_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getCaptain_name() {
        return captain_name;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public String getDatetimedeadline() { return datetimedeadline; }

    public String getSlots_left() { return slots_left; }

    public String getOrder_id() { return order_id; }

}

/*
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
}*/

package com.example.hungrytogetherandroidapplication.my_orders;

public class MyOrderItem {


    private String restaurant_name;
    private String captain_name;
    private String datetimedeadline;
    private String meal_label;
    private String meal_cost;
    private String captain_fee;
    private String total_cost;

    public MyOrderItem(String restaurant_name, String captain_name, String datetimedeadline, String meal_label, String meal_cost, String captain_fee, String total_cost) {
        this.restaurant_name = restaurant_name;
        this.captain_name = captain_name;
        this.datetimedeadline = datetimedeadline;
        this.meal_label = meal_label;
        this.meal_cost = meal_cost;
        this.captain_fee = captain_fee;
        this.total_cost = total_cost;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getCaptain_name() {
        return captain_name;
    }

    public String getDatetimedeadline() {
        return datetimedeadline;
    }

    public String getMeal_label() {
        return meal_label;
    }

    public String getMeal_cost() {
        return meal_cost;
    }

    public String getCaptain_fee() {
        return captain_fee;
    }

    public String getTotal_cost() {
        return total_cost;
    }
}
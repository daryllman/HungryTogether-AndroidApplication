package com.example.hungrytogetherandroidapplication.ordering_portal;

public class MyOrder {

    private String meal_label;
    /**
     *
     */
    private String meal_cost;
    private String total_cost;

    MyOrder(String meal_label, String meal_cost, String total_cost){
        this.meal_label=meal_label;
        this.meal_cost=meal_cost;
        this.total_cost=total_cost;
    }

    public String getMeal_label(){
        return meal_label;
    }
    public String getMeal_cost() {
        return meal_cost;
    }
    public String getTotal_cost() {
        return total_cost;
    }
}

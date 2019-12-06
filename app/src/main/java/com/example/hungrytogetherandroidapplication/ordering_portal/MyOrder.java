package com.example.hungrytogetherandroidapplication.ordering_portal;

public class MyOrder {

    private String meal_label;
    private String sailor_name;
    private String meal_cost;
    private String total_cost;

    MyOrder(String meal_cost, String meal_label, String sailor_name, String total_cost){
        this.meal_cost=meal_cost;
        this.meal_label=meal_label;
        this.sailor_name=sailor_name;
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
    public String getSailor_name() { return sailor_name;}
}

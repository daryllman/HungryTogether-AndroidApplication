package com.example.hungrytogetherandroidapplication.food_captain_orders;

public class SailorOrderItem {

    private String meal_cost;
    private String meal_label;
    private String sailor_name;
    private String total_cost;


    public SailorOrderItem() {
        // empty constructor
    }

    public SailorOrderItem(String meal_cost, String meal_label, String sailor_name, String total_cost) {
        this.meal_cost = meal_cost;
        this.meal_label = meal_label;
        this.sailor_name = sailor_name;
        this.total_cost = total_cost;
    }

    //NOTE!!
    // For some reason, firebase only recognises if your getter functions are named in this very specific way - in accordance to the names of your variables too
    // technically the name does not matter as you have to determine which getter func to use
    // 1. Name of variables must be exactly the same as the ones in firebase
    // 2. Name of getter function must be in this specific format - according to variable name
    // 3. Last tip: Just use String. ie make sure we convert and send as String to firebase; easier to retrive as String too
    // If you use something else, theres some deserialising problem that you have to fix due to the value types of firebase data type


    public String getSailor_name() {
        return sailor_name;
    }

    public String getMeal_cost() {
        return meal_cost;
    }

    public String getMeal_label() {
        return meal_label;
    }

    public String getTotal_cost() {
        return total_cost;
    }
}

/* old version for old database structure....
public class SailorOrderItem {

    private String sailor_name;
    private String timejoined;
    private String fooditem;
    private String cost;


    public SailorOrderItem() {
        // empty constructor
    }

    public SailorOrderItem(String sailor_name, String timejoined, String fooditem, String cost) {
        this.sailor_name = sailor_name;
        this.timejoined = timejoined;
        this.fooditem = fooditem;
        this.cost = cost;
    }

    //NOTE!!
    // For some reason, firebase only recognises if your getter functions are named in this very specific way - in accordance to the names of your variables too
    // technically the name does not matter as you have to determine which getter func to use
    // 1. Name of variables must be exactly the same as the ones in firebase
    // 2. Name of getter function must be in this specific format - according to variable name
    // 3. Last tip: Just use String. ie make sure we convert and send as String to firebase; easier to retrive as String too
    // If you use something else, theres some deserialising problem that you have to fix due to the value types of firebase data type


    public String getCost() {
        return cost;
    }

    public String getFooditem() {
        return fooditem;
    }

    public String getSailor_name() {
        return sailor_name;
    }

    public String getTimejoined() {
        return timejoined;
    }

}*/
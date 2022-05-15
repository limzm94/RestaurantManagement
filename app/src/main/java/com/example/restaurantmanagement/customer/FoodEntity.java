package com.example.restaurantmanagement.customer;

public class FoodEntity {

    private String foodName;

    private String foodDesc;
    private double price;

    // Constructor
    public FoodEntity(String foodName, String foodDesc, double price) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.price = price;
    }

    // Getter and Setter

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

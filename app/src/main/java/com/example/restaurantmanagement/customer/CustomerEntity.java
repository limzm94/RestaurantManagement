package com.example.restaurantmanagement.customer;

public class CustomerEntity extends FoodEntity {

    private String customerName;
    private int quantity;

    public CustomerEntity(String foodName, String foodDesc, double price, int quantity, String customerName) {
        super(foodName, foodDesc, price);
        this.quantity = quantity;
        this.customerName = customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
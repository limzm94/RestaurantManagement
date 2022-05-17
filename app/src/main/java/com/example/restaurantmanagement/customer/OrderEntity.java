package com.example.restaurantmanagement.customer;

public class OrderEntity extends FoodEntity {

    private String customerName, isFulfilled;
    private int quantity, orderId;

    public OrderEntity(String foodName, String foodDesc, double price, int quantity, String customerName, String isFulfilled, int orderId, int foodKey) {
        super(foodName, foodDesc, price, foodKey);
        this.quantity = quantity;
        this.customerName = customerName;
        this.isFulfilled = isFulfilled;
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(String isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
package com.example.restaurantmanagement.customer.Entity;

import java.io.Serializable;

public class OrderObject extends FoodObject implements Serializable {

    private String customerName, isFulfilled, orderDate;
    private int quantity;
    private int orderId;


    private int discount;

    public OrderObject(String foodName, String foodDesc, double price, int quantity, String customerName, String isFulfilled, int orderId, int foodKey, int discount, String orderDate) {
        super(foodName, foodDesc, price, foodKey);
        this.quantity = quantity;
        this.customerName = customerName;
        this.isFulfilled = isFulfilled;
        this.orderId = orderId;
        this.discount = discount;
        this.orderDate = orderDate;
    }


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public String getOrderDate(){
        return orderDate;
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
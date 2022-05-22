package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;

public class CheckCustomer {
    Context context;

    public CheckCustomer(Context context) {
        this.context = context;
    }

    public boolean checkCustomer(String customerName) {
        OrderEntity orderEntity = new OrderEntity(context);
        return orderEntity.checkCustomer(customerName);
    }
}
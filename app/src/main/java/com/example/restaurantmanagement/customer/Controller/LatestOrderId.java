package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;

public class LatestOrderId {
    private final Context context;

    public LatestOrderId(Context context) {
        this.context = context;
    }

    public int getOrderId() {
        OrderEntity orderEntity = new OrderEntity(context);
        return orderEntity.getOrderId();
    }
}
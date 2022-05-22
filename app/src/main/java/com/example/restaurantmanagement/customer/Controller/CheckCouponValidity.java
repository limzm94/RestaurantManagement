package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;

public class CheckCouponValidity {
    Context context;

    public CheckCouponValidity(Context context) {
        this.context = context;
    }

    public boolean couponValidity(String couponCode) {
        OrderEntity orderEntity = new OrderEntity(context);
        return orderEntity.checkCouponValidity(couponCode);
    }
}

package com.example.restaurantmanagement.manager.Controller;

import android.content.Context;

import com.example.restaurantmanagement.manager.Entity.CouponEntity;

public class CreateCoupon {
    Context context;

    public CreateCoupon(Context context) {
        this.context = context;
    }

    public boolean insertCoupon(String couponCode,String couponDesc,int discount,String status) {
        CouponEntity couponEntity = new CouponEntity(context);
        return couponEntity.insertCoupon(couponCode, couponDesc, discount, status);
    }
}
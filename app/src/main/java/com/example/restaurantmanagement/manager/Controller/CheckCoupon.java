package com.example.restaurantmanagement.manager.Controller;

import android.content.Context;

import com.example.restaurantmanagement.manager.Entity.CouponEntity;

public class CheckCoupon {
    Context context;

    public CheckCoupon(Context context) {
        this.context = context;
    }

    public boolean couponCheck(String couponCode) {
        CouponEntity couponEntity = new CouponEntity(context);
        return couponEntity.checkCoupon(couponCode);
    }
}

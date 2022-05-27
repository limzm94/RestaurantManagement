package com.example.restaurantmanagement.manager.Controller.Coupon;

import android.content.Context;

import com.example.restaurantmanagement.manager.Entity.CouponEntity;

public class EditCoupon {
    private final Context context;
    public EditCoupon(Context context) {
        this.context = context;
    }

    public void updateCoupon(String couponCode, String couponDesc, int discount, String status, int couponId) {
        CouponEntity couponEntity = new CouponEntity(context);
        couponEntity.updateCoupon(couponCode, couponDesc, discount, status, couponId);
    }
}
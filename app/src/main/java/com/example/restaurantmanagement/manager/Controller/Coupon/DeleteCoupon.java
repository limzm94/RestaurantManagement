package com.example.restaurantmanagement.manager.Controller.Coupon;

import android.content.Context;

import com.example.restaurantmanagement.manager.Entity.CouponEntity;

public class DeleteCoupon {
    private final Context context;

    public DeleteCoupon(Context context) {
        this.context = context;
    }

    public void deleteCoupon(int couponCode) {
        CouponEntity couponEntity = new CouponEntity(context);
        couponEntity.removeCoupon(couponCode);
    }
}

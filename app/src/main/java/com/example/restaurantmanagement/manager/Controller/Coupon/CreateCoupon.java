package com.example.restaurantmanagement.manager.Controller.Coupon;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.manager.Entity.CouponEntity;

public class CreateCoupon {
    private final Context context;

    public CreateCoupon(Context context) {
        this.context = context;
    }

    public void insertCoupon(String couponCode,String couponDesc,int discount,String status) {
        CouponEntity couponEntity = new CouponEntity(context);
        if (!couponEntity.checkCoupon(couponCode)) {
            if (couponEntity.insertCoupon(couponCode, couponDesc, discount, status)) {
                Toast.makeText(context, "Coupon created successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Coupon creation failed", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Coupon code already exists!", Toast.LENGTH_LONG).show();
        }
    }
}
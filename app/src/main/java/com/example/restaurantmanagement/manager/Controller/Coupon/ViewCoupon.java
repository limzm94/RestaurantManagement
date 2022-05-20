package com.example.restaurantmanagement.manager.Controller.Coupon;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;
import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.manager.Entity.CouponEntity;
import com.example.restaurantmanagement.manager.Entity.CouponObject;

import java.util.ArrayList;

public class ViewCoupon {
    Context context;

    public ViewCoupon(Context context) {
        this.context = context;
    }

    public ArrayList<CouponObject> showCoupon(){
        CouponEntity couponEntity = new CouponEntity(context);
        return couponEntity.viewCouponList();
    }
}
package com.example.restaurantmanagement.manager.Entity;


import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class CouponEntity {
    private Context context;

    public CouponEntity(Context context) {
        this.context = context;
    }

    public ArrayList<CouponObject> viewCouponList(){
        DBHandler DB = new DBHandler(context);
        ArrayList<CouponObject> couponList = new ArrayList<>();

        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<String> allCouponCode = DB.listColumnsDataStr("coupons", "code");
        ArrayList<Integer> allCouponKey = DB.listColumnsDataInt("coupons", "couponId");
        ArrayList<String> allCouponDesc = DB.listColumnsDataStr("coupons", "description");
        ArrayList<String> allCouponStatus = DB.listColumnsDataStr("coupons", "isActive");
        ArrayList<Integer> addDiscount = DB.listColumnsDataInt("coupons", "discount");
        int count = 0;
        while (allCouponKey.size() > count) {
            couponList.add(new CouponObject(allCouponCode.get(count), allCouponDesc.get(count), allCouponStatus.get(count), allCouponKey.get(count),addDiscount.get(count)));
            count++;
        }
        return  couponList;
    }

    public Boolean checkCoupon(String couponCode) {
        DBHandler DB = new DBHandler(context);
        return DB.checkCouponCode(couponCode);
    }

    public Boolean insertCoupon(String couponCode,String couponDesc,int discount,String status) {
        DBHandler DB = new DBHandler(context);
        return DB.insertCouponData(couponCode, couponDesc, discount, status);
    }

    public void updateCoupon(String couponCode, String couponDesc, int discount, String status, int couponId) {
        DBHandler DB = new DBHandler(context);
        DB.updateCouponInfo(couponCode, couponDesc, discount, status, couponId);
    }

    public void removeCoupon(int couponCode) {
        DBHandler DB = new DBHandler(context);
        DB.deleteCoupon(couponCode);
    }
}
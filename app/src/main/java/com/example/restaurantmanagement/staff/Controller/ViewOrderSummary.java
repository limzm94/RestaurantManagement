package com.example.restaurantmanagement.staff.Controller;


import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;
import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.manager.Entity.CouponEntity;
import com.example.restaurantmanagement.manager.Entity.CouponObject;
import com.example.restaurantmanagement.staff.Entity.StaffEntity;

import java.util.ArrayList;

public class ViewOrderSummary {
    private final Context context;

    public ViewOrderSummary(Context context) {
        this.context = context;
    }

    public ArrayList<OrderObject> showOrderSummary(int orderId){
        StaffEntity staffEntity = new StaffEntity(context);
        return staffEntity.listOrder(orderId);
    }
}
package com.example.restaurantmanagement.staff.Entity;


import android.content.Context;

import com.example.restaurantmanagement.manager.Entity.CouponObject;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class StaffEntity {
    private Context context;

    public StaffEntity(Context context) {
        this.context = context;
    }

    public ArrayList<Integer> viewOrderNum(String fulfill){
        DBHandler DB = new DBHandler(context);
        return DB.listUnfulfilled("OrderDetail", "OrderId");
    }

}
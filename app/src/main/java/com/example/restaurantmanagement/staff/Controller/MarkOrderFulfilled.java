package com.example.restaurantmanagement.staff.Controller;

import android.content.Context;

import com.example.restaurantmanagement.staff.Entity.StaffEntity;

import java.util.ArrayList;

public class MarkOrderFulfilled {
    private final Context context;

    public MarkOrderFulfilled(Context context) {
        this.context = context;
    }

    public void markFulFilled(int orderId) {
        StaffEntity staffEntity = new StaffEntity(context);
        staffEntity.markFulfilled(orderId);
    }
}
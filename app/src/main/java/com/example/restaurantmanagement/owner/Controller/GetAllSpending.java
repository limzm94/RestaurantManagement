package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

import java.util.ArrayList;

public class GetAllSpending {
    Context context;

    public GetAllSpending(Context context) {
        this.context = context;
    }

    public ArrayList<OrderObject> getAllSpending() {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getSpendingAnalytics();
    }
}

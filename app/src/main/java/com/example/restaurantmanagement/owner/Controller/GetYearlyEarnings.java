package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetYearlyEarnings {
    private final Context context;

    public GetYearlyEarnings(Context context) {
        this.context = context;
    }

    public float getYearlyEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getYearlyEarnings(date);
    }
}

package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetMonthlyEarnings {
    private final Context context;

    public GetMonthlyEarnings(Context context) {
        this.context = context;
    }

    // todo: float this mf
    public float getMonthlyEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getMonthlyEarnings(date);
    }
}

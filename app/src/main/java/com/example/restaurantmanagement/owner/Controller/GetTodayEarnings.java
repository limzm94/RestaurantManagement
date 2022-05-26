package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetTodayEarnings {
    Context context;

    public GetTodayEarnings(Context context) {
        this.context = context;
    }

    // todo: float this mf
    public float getTodayEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getDailyEarnings(date);
    }
}

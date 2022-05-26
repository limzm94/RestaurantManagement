package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetMenuRecommendationToday {
    Context context;

    public GetMenuRecommendationToday(Context context) {
        this.context = context;
    }

    // menu recommendation
    public String getMenuRecommendationToday(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getTdyFoodPreference(date);
    }
}

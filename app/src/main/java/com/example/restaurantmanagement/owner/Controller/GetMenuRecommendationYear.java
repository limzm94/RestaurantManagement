package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetMenuRecommendationYear {
    private final Context context;

    public GetMenuRecommendationYear(Context context) {
        this.context = context;
    }

    public String getMenuRecommendationYear(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getYearFoodPreference(date);
    }
}

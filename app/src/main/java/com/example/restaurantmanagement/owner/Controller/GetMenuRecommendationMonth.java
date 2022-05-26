package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetMenuRecommendationMonth {
    Context context;

    public GetMenuRecommendationMonth(Context context) {
        this.context = context;
    }


    public String getMenuRecommendationMonth(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getMthFoodPreference(date);
    }
}

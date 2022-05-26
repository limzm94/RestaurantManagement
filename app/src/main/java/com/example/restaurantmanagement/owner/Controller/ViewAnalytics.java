package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;
import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;
import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

import java.util.ArrayList;

public class ViewAnalytics {
    Context context;

    public ViewAnalytics(Context context) {
        this.context = context;
    }


    public ArrayList<OrderObject> getAllSpending() {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getSpendingAnalytics();
    }

    public int getTodayEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getDailyEarnings(date);
    }

    public int getMonthlyEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getMonthlyEarnings(date);
    }

    public float getYearlyEarnings(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getYearlyEarnings(date);
    }

    // menu recommendation
    public String getMenuRecommendationToday(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getTdyFoodPreference(date);
    }

    public String getMenuRecommendationMonth(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getMthFoodPreference(date);
    }

    public String getMenuRecommendationYear(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getYearFoodPreference(date);
    }

    public int getFrequencyToday(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisit(date);
    }

    public int getFrequencyYear(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisitYear(date);
    }

    public int getFrequencyMonth(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisitMonth(date);
    }

}


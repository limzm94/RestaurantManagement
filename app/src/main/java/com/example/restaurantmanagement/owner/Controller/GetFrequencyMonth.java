package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetFrequencyMonth {
    private final Context context;

    public GetFrequencyMonth(Context context) {
        this.context = context;
    }

    public int getFrequencyMonth(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisitMonth(date);
    }
}

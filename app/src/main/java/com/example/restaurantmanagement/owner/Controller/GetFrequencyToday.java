package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetFrequencyToday {
    Context context;

    public GetFrequencyToday(Context context) {
        this.context = context;
    }

    public int getFrequencyToday(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisit(date);
    }
}

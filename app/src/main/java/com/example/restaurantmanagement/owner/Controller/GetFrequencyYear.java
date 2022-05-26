package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;

import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

public class GetFrequencyYear {
    Context context;

    public GetFrequencyYear(Context context) {
        this.context = context;
    }

    public int getFrequencyYear(String date) {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.getFrqVisitYear(date);
    }
}

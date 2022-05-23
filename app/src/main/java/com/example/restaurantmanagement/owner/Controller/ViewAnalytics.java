package com.example.restaurantmanagement.owner.Controller;

import android.content.Context;
import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;
import com.example.restaurantmanagement.owner.Entity.AnalyticsEntity;

import java.util.ArrayList;

public class ViewAnalytics {
    Context context;

    public ViewAnalytics(Context context) {
        this.context = context;
    }

    public ArrayList<FoodObject> showFoodMenu() {
        FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
        return foodMenuEntity.listFoods();
    }

    public boolean forTesting() {
        AnalyticsEntity analyticsEntity = new AnalyticsEntity(context);
        return analyticsEntity.insertAnalytics();
    }
}


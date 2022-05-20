package com.example.restaurantmanagement.manager.Controller.FoodMenu;

import android.content.Context;
import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;

public class CheckMenuItem {
    Context context;

    public CheckMenuItem(Context context) {
        this.context = context;
    }

    public boolean checkFoodItem(String foodName) {
        FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
        return foodMenuEntity.checkFood(foodName);
    }
}
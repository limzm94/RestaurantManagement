package com.example.restaurantmanagement.manager.Controller.FoodMenu;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;

public class DeleteFoodMenu {
    Context context;

    public DeleteFoodMenu(Context context) {
        this.context = context;
    }

    public void deleteFoodMenu(int menuId) {
        FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
        foodMenuEntity.deleteFood(menuId);
    }
}
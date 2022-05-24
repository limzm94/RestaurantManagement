package com.example.restaurantmanagement.manager.Controller.FoodMenu;

import android.content.Context;
import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;

public class EditFoodMenu {
    Context context;

    public EditFoodMenu(Context context) {
        this.context = context;
    }

    public void updateFoodItem(String foodName, String foodDesc, double price, int foodKey) {
        CheckMenuItem checkMenuItem = new CheckMenuItem(context);
        if (checkMenuItem.checkFoodItem(foodName)) {
            FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
            foodMenuEntity.editFood(foodName, foodDesc, price, foodKey);
        } else {

        }

    }
}

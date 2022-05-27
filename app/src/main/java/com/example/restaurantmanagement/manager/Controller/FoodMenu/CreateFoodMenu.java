package com.example.restaurantmanagement.manager.Controller.FoodMenu;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;

public class CreateFoodMenu {
    private final Context context;

    public CreateFoodMenu(Context context) {
        this.context = context;
    }

    public void insertFoodMenu(String foodName,String foodDesc,double price) {
        FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
        if (!foodMenuEntity.checkFood(foodName)) {
            if (foodMenuEntity.insertFood(foodName, foodDesc, price)) {
                Toast.makeText(context, "Registered successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Food Item already Exist!", Toast.LENGTH_LONG).show();
        }
    }
}
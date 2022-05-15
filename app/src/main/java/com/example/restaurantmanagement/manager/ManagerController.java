package com.example.restaurantmanagement.manager;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.admin.CreateAccount;
import com.example.restaurantmanagement.utility.DBHandler;

public class ManagerController {


    public void insertFood(Context context, String foodName, String foodDesc, double price) {
        DBHandler DB = new DBHandler(context);

        Boolean checkFoodName = DB.checkFoodName(foodName);
        if (!checkFoodName) {
            Boolean insert = DB.insertFoodData(foodName, foodDesc, price);
            if (insert) {
                Toast.makeText(context, "Menu added successfully", Toast.LENGTH_LONG).show();
            }
        }
    }
}

package com.example.restaurantmanagement.manager.Entity;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class FoodMenuEntity {
    Context context;

    public FoodMenuEntity(Context context) {
        this.context = context;
    }

    public ArrayList<FoodObject> listFoods(){
        DBHandler DB = new DBHandler(context);
        ArrayList<FoodObject> foodList = new ArrayList<>();
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<Integer> allFoodKey = DB.listColumnsDataInt("foods", "menuId");
        System.out.println(allFoodKey);
        ArrayList<String> allFoodName = DB.listColumnsDataStr("foods", "name");
        System.out.println(allFoodName);
        ArrayList<String> allFoodDesc = DB.listColumnsDataStr("foods", "description");
        System.out.println(allFoodDesc);
        ArrayList<Double> allPrice = DB.listColumnsDataDbl("foods", "price");
        System.out.println(allPrice);
        int count = 0;
        while (allFoodName.size() > count) {
            foodList.add(new FoodObject(allFoodName.get(count), allFoodDesc.get(count), allPrice.get(count),allFoodKey.get(count)));
            count++;
        }
        return foodList;
    }

    public boolean checkFood(String foodName) {
        DBHandler DB = new DBHandler(context);
        return DB.checkFoodName(foodName);
    }

    public boolean insertFood(String foodName, String foodDesc, double price) {
        DBHandler DB = new DBHandler(context);
        return DB.insertFoodData(foodName, foodDesc, price);
    }

    public void editFood(String foodName, String foodDesc, double price, int foodKey) {
        DBHandler DB = new DBHandler(context);
        DB.updateFoodInfo(foodName, foodDesc, price, foodKey);
    }

    public void deleteFood(int menuId) {
        DBHandler DB = new DBHandler(context);
        DB.deleteMenuItem(menuId);
    }
}


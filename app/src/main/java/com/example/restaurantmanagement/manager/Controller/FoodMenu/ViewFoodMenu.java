package com.example.restaurantmanagement.manager.Controller.FoodMenu;

import android.content.Context;
import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.manager.Entity.FoodMenuEntity;
import java.util.ArrayList;

public class ViewFoodMenu {
    private final Context context;

    public ViewFoodMenu(Context context) {
        this.context = context;
    }

    public ArrayList<FoodObject> showFoodMenu() {
        FoodMenuEntity foodMenuEntity = new FoodMenuEntity(context);
        return foodMenuEntity.listFoods();
    }
}

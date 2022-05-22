package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class ShowMenu {
    Context context;

    public ShowMenu(Context context) {
        this.context = context;
    }

    public ArrayList<OrderObject> displayMenu() {
        OrderEntity orderEntity = new OrderEntity(context);
        return orderEntity.populateMenu();
    }
}

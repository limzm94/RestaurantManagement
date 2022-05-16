package com.example.restaurantmanagement.manager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.CheckoutActivity;
import com.example.restaurantmanagement.customer.CustomerController;
import com.example.restaurantmanagement.customer.OrderEntity;
import com.example.restaurantmanagement.manager.ManagerController;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class FoodMenu extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Button createFoodItemBtn = findViewById(R.id.createFoodItemBtn);
        RecyclerView adminRV = findViewById(R.id.idRVFoodManager);


        // need to do this in controller
        DBHandler DB = new DBHandler(this);
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<String> allFoodName = DB.listColumnsDataStr("foods", "name");
        System.out.println(allFoodName);
        ArrayList<String> allFoodDesc = DB.listColumnsDataStr("foods", "description");
        System.out.println(allFoodDesc);
        ArrayList<Double> allPrice = DB.listColumnsDataDbl("foods", "price");
        System.out.println(allPrice);
// need to do this in controller
        ArrayList<OrderEntity> foodList = new ArrayList<>();
        int count = 0;
        while (allFoodName.size() > count) {
            foodList.add(new OrderEntity(allFoodName.get(count), allFoodDesc.get(count), allPrice.get(count),
                    0, "Customer Name", "Unfulfilled", 0));
            count++;
        }
        // we are initializing our adapter class and passing our arraylist to it.
        CustomerController foodAdapter = new CustomerController(foodList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(foodAdapter);


        createFoodItemBtn.setOnClickListener(v -> {

        });

    }
}
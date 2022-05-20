package com.example.restaurantmanagement.manager.Boundary.FoodMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.ViewFoodMenu;

public class FoodMenuView extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ViewFoodMenu viewFoodMenu = new ViewFoodMenu(FoodMenuView.this);
        Button createFoodItemBtn = findViewById(R.id.createFoodItemBtn);
        RecyclerView adminRV = findViewById(R.id.idRVFoodManager);
        // we are initializing our adapter class and passing our arraylist to it.
        FoodMenuViewHolder foodAdapter = new FoodMenuViewHolder(FoodMenuView.this, viewFoodMenu.showFoodMenu());
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(foodAdapter);

        createFoodItemBtn.setOnClickListener(v -> {
            Intent createFood = new Intent(FoodMenuView.this, CreateFoodItem.class);
            startActivity(createFood);
            finish();
        });
    }
}
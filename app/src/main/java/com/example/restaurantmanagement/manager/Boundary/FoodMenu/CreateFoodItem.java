package com.example.restaurantmanagement.manager.Boundary.FoodMenu;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.CreateFoodMenu;

public class CreateFoodItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        CreateFoodMenu createFoodMenu = new CreateFoodMenu(CreateFoodItem.this);
        EditText foodNameText = findViewById(R.id.foodName_create);
        EditText foodDescText = findViewById(R.id.foodDesc_create);
        EditText priceText = findViewById(R.id.price_create);
        Button createBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        createBtn.setOnClickListener(v -> {
            String foodName = foodNameText.getText().toString();
            String foodDesc = foodDescText.getText().toString();
            double price = Double.parseDouble(priceText.getText().toString());

            if (foodName.equals("") || foodDesc.equals("") || price == 0)
                Toast.makeText(CreateFoodItem.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                createFoodMenu.insertFoodMenu(foodName, foodDesc, price);
                Intent createAcc = new Intent(CreateFoodItem.this, FoodMenuView.class);
                startActivity(createAcc);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> finish());
    }
}
package com.example.restaurantmanagement.manager.Boundary.FoodMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.CheckMenuItem;
import com.example.restaurantmanagement.manager.Controller.FoodMenu.EditFoodMenu;

public class EditFoodItem extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        EditFoodMenu editFoodMenu = new EditFoodMenu(EditFoodItem.this);
        CheckMenuItem checkMenuItem = new CheckMenuItem(EditFoodItem.this);
        EditText foodNameText = findViewById(R.id.foodName_create);
        EditText foodDescText = findViewById(R.id.foodDesc_create);
        EditText priceText = findViewById(R.id.price_create);
        Button editBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        editBtn.setText("Confirm Edit");

        // get the data from the cardView
        int foodKey = getIntent().getIntExtra("foodKey",0);
        String foodName = getIntent().getStringExtra("foodName");
        String foodDesc = getIntent().getStringExtra("foodDesc");
        double price = getIntent().getDoubleExtra("price", 0);

        foodNameText.setText(foodName);
        foodDescText.setText(foodDesc);
        priceText.setText(Double.toString(price));

        editBtn.setOnClickListener(v -> {
            String editedFoodName = foodNameText.getText().toString();
            String editedFoodDesc = foodDescText.getText().toString();
            double editedPrice = Double.parseDouble(priceText.getText().toString());

            if (editedFoodName.equals("") || editedFoodDesc.equals("") || editedPrice == 0)
                Toast.makeText(EditFoodItem.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else if (checkMenuItem.checkFoodItem(editedFoodName)) {
                Toast.makeText(EditFoodItem.this, "Cannot change menu item to have same name", Toast.LENGTH_SHORT).show();
            } else {
                editFoodMenu.updateFoodItem(editedFoodName, editedFoodDesc, editedPrice, foodKey);
                Intent createAcc = new Intent(EditFoodItem.this, FoodMenuView.class);
                startActivity(createAcc);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
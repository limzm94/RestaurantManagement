package com.example.restaurantmanagement.manager;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.AdminUI;
import com.example.restaurantmanagement.utility.DBHandler;

public class EditFoodItem extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        DBHandler DB = new DBHandler(this);
        EditText foodNameText = findViewById(R.id.foodName_create);
        EditText foodDescText = findViewById(R.id.foodDesc_create);
        EditText priceText = findViewById(R.id.price_create);
        Button editBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        editBtn.setText("Edit");

        // get the data from the cardview
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
            else {
                DB.updateFoodInfo(editedFoodName, editedFoodDesc, editedPrice, foodKey);

                Intent createAcc = new Intent(EditFoodItem.this, FoodMenu.class);
                startActivity(createAcc);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
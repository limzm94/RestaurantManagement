package com.example.restaurantmanagement.manager;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;

public class ManagerUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Button foodMenuBtn = findViewById(R.id.mngrFoodMenuBtn);
        Button couponBtn = findViewById(R.id.mngrCouponBtn);
        Button logoutBtn = findViewById(R.id.logout_btn);



        foodMenuBtn.setOnClickListener(v -> {
            Intent admin = new Intent(ManagerUI.this, FoodMenu.class);
            startActivity(admin);
            finish();
        });

        couponBtn.setOnClickListener(v -> {
            Intent customer = new Intent(ManagerUI.this, Coupon.class);
            startActivity(customer);
            finish();
        });

        logoutBtn.setOnClickListener(v -> finish());


    }
}
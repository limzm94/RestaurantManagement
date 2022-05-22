package com.example.restaurantmanagement.manager.Boundary;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Boundary.Coupon.Coupon;
import com.example.restaurantmanagement.manager.Boundary.FoodMenu.FoodMenuView;

public class ManagerView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Button foodMenuBtn = findViewById(R.id.mngrFoodMenuBtn);
        Button couponBtn = findViewById(R.id.mngrCouponBtn);
        Button logoutBtn = findViewById(R.id.logout_btn);

        foodMenuBtn.setOnClickListener(v -> {
            Intent admin = new Intent(ManagerView.this, FoodMenuView.class);
            startActivity(admin);
            finish();
        });

        couponBtn.setOnClickListener(v -> {
            Intent customer = new Intent(ManagerView.this, Coupon.class);
            startActivity(customer);
            finish();
        });

        logoutBtn.setOnClickListener(v -> finish());
    }
}
package com.example.restaurantmanagement.staff.Boundary;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Boundary.CustomerView;
import com.example.restaurantmanagement.manager.Boundary.Coupon.Coupon;

public class StaffView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        Button orderFoodBtn = findViewById(R.id.orderFoodBtn);
        Button manageOrderBtn = findViewById(R.id.manageOrderBtn);
        Button logoutBtn = findViewById(R.id.logout_btn);

        orderFoodBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StaffView.this, CustomerView.class);
            intent.putExtra("accountRole","Staff");
            intent.putExtra("customerName","");
            startActivity(intent);
            finish();
        });

        manageOrderBtn.setOnClickListener(v -> {
            Intent customer = new Intent(StaffView.this, ManageOrderView.class);
            startActivity(customer);
            finish();
        });

        logoutBtn.setOnClickListener(v -> finish());
    }
}
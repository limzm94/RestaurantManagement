package com.example.restaurantmanagement.owner;
// need to implement navigation drawer for owner or else the activity flow will be weird for him

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.Boundary.AdminPageView;
import com.example.restaurantmanagement.customer.Boundary.CustomerView;
import com.example.restaurantmanagement.manager.Boundary.ManagerView;
import com.example.restaurantmanagement.owner.Boundary.OwnerView;

public class OwnerUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        Button ownerBtn = findViewById(R.id.owner_btn);
        Button adminBtn = findViewById(R.id.admin_btn);
        Button managerBtn = findViewById(R.id.manager_btn);
        Button staffBtn = findViewById(R.id.staff_btn);
        Button customerBtn = findViewById(R.id.customer_btn);
        Button logoutBtn = findViewById(R.id.logout_btn);

        ownerBtn.setOnClickListener(v -> {
            Intent createAcc = new Intent(OwnerUI.this, OwnerView.class);
            startActivity(createAcc);
            finish();
        });

        adminBtn.setOnClickListener(v -> {
            Intent admin = new Intent(OwnerUI.this, AdminPageView.class);
            startActivity(admin);
            finish();
        });

        customerBtn.setOnClickListener(v -> {
            Intent customer = new Intent(OwnerUI.this, CustomerView.class);
            startActivity(customer);
            finish();
        });

        managerBtn.setOnClickListener(v -> {
            Intent manager = new Intent(OwnerUI.this, ManagerView.class);
            startActivity(manager);
            finish();
        });

        logoutBtn.setOnClickListener(v -> finish());


    }
}

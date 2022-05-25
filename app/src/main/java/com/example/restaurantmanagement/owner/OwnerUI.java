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
import com.example.restaurantmanagement.staff.Boundary.StaffView;

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
            Intent intent = new Intent(OwnerUI.this, OwnerView.class);
            intent.putExtra("accountRole","Owner");
            startActivity(intent);
        });

        staffBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerUI.this, StaffView.class);
            intent.putExtra("accountRole","Owner");
            startActivity(intent);
        });

        adminBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerUI.this, AdminPageView.class);
            intent.putExtra("accountRole","Owner");
            startActivity(intent);
        });

        customerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerUI.this, CustomerView.class);
            intent.putExtra("accountRole","Owner");
            intent.putExtra("customerName","");
            startActivity(intent);
        });

        managerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerUI.this, ManagerView.class);
            intent.putExtra("accountRole","Owner");
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(v -> finish());
    }
}

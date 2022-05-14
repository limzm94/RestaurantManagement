package com.example.restaurantmanagement.owner;
// need to implement navigation drawer for owner or else the activity flow will be weird for him

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.AdminUI;
import com.example.restaurantmanagement.admin.CreateAccount;

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

        /*ownerBtn.setOnClickListener(v -> {
            Intent createAcc = new Intent(OwnerUI.this, CreateAccount.class);
            startActivity(createAcc);
            finish();
        });*/

        adminBtn.setOnClickListener(v -> {
            Intent createAcc = new Intent(OwnerUI.this, AdminUI.class);
            startActivity(createAcc);
            finish();
        });


    }
}

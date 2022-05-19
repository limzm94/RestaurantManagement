package com.example.restaurantmanagement.manager.Boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.utility.DBHandler;

public class CreateCoupon extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coupon);
        DBHandler DB = new DBHandler(this);
        Spinner statusSpinner = findViewById(R.id.couponStatus_create);
        EditText couponCodeText = findViewById(R.id.couponCode_create);
        EditText couponDescText = findViewById(R.id.couponDesc_create);
        EditText discountText = findViewById(R.id.discount_create);
        Button createBtn = findViewById(R.id.create_btn);
        Button cancelBtn = findViewById(R.id.cancel_btn);

        // set the items in the spinner
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);

        createBtn.setOnClickListener(v -> {

            String couponCode = couponCodeText.getText().toString();
            String couponDesc = couponDescText.getText().toString();
            int discount = Integer.parseInt(discountText.getText().toString());
            String status = statusSpinner.getSelectedItem().toString();


            if (couponCode.equals("") || couponDesc.equals("") || discount == 0)
                Toast.makeText(CreateCoupon.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUser = DB.checkCouponCode(couponCode);
                if (!checkUser) {
                    Boolean insert = DB.insertCouponData(couponCode, couponDesc, discount, status);
                    if (insert) {
                        Toast.makeText(CreateCoupon.this, "Coupon created successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(CreateCoupon.this, "Coupon creation failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CreateCoupon.this, "Coupon code already exists!", Toast.LENGTH_LONG).show();
                }

                Intent createCoupon = new Intent(CreateCoupon.this, Coupon.class);
                startActivity(createCoupon);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
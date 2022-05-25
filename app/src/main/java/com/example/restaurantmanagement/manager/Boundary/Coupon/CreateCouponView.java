package com.example.restaurantmanagement.manager.Boundary.Coupon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Controller.Coupon.CreateCoupon;

public class CreateCouponView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coupon);
        CreateCoupon createCoupon = new CreateCoupon(CreateCouponView.this);
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
                Toast.makeText(CreateCouponView.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else if (discount > 100) {
                Toast.makeText(CreateCouponView.this, "Invalid discount percentage", Toast.LENGTH_SHORT).show();
            }
            else {
                createCoupon.insertCoupon(couponCode, couponDesc, discount, status);
                Intent couponIntent = new Intent(CreateCouponView.this, Coupon.class);
                startActivity(couponIntent);
                finish();
            }
        });

        cancelBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
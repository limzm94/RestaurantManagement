package com.example.restaurantmanagement.manager.Boundary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.Controller.ViewCoupon;

public class Coupon extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        Button createCouponBtn = findViewById(R.id.createCouponBtn);
        RecyclerView couponRV = findViewById(R.id.idRVCouponManager);
        ViewCoupon viewCoupon = new ViewCoupon(Coupon.this);


        // need to do this in controller

// need to do this in controller

        // we are initializing our adapter class and passing our arraylist to it.
        CouponViewHolder couponAdapter = new CouponViewHolder(Coupon.this, viewCoupon.showCoupon());
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutManager and adapter to our recycler view.
        couponRV.setLayoutManager(linearLayoutManager);
        couponRV.setAdapter(couponAdapter);


        createCouponBtn.setOnClickListener(v -> {
            Intent createFood = new Intent(Coupon.this, CreateCouponView.class);
            startActivity(createFood);
            finish();
        });

    }
}
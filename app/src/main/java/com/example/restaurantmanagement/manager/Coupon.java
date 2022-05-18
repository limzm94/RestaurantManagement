package com.example.restaurantmanagement.manager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.FoodEntity;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class Coupon extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        Button createCouponBtn = findViewById(R.id.createCouponBtn);
        RecyclerView couponRV = findViewById(R.id.idRVCouponManager);


        // need to do this in controller
        DBHandler DB = new DBHandler(this);
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<String> allCouponCode = DB.listColumnsDataStr("coupons", "code");
        System.out.println(allCouponCode);
        ArrayList<Integer> allCouponKey = DB.listColumnsDataInt("coupons", "couponId");
        System.out.println(allCouponKey);
        ArrayList<String> allCouponDesc = DB.listColumnsDataStr("coupons", "description");
        System.out.println(allCouponDesc);
        ArrayList<String> allCouponStatus = DB.listColumnsDataStr("coupons", "isActive");
        System.out.println(allCouponStatus);
        ArrayList<Integer> addDiscount = DB.listColumnsDataInt("coupons", "discount");
        System.out.println(addDiscount);
// need to do this in controller
        ArrayList<CouponEntity> couponList = new ArrayList<>();
        int count = 0;
        while (allCouponKey.size() > count) {
            couponList.add(new CouponEntity(allCouponCode.get(count), allCouponDesc.get(count), allCouponStatus.get(count), allCouponKey.get(count),addDiscount.get(count)));
            count++;
        }
        // we are initializing our adapter class and passing our arraylist to it.
        CouponController couponAdapter = new CouponController(Coupon.this, couponList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutManager and adapter to our recycler view.
        couponRV.setLayoutManager(linearLayoutManager);
        couponRV.setAdapter(couponAdapter);


        createCouponBtn.setOnClickListener(v -> {
            Intent createFood = new Intent(Coupon.this, CreateCoupon.class);
            startActivity(createFood);
            finish();
        });

    }
}
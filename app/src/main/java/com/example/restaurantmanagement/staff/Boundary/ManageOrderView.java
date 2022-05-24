package com.example.restaurantmanagement.staff.Boundary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.manager.Boundary.Coupon.CouponViewHolder;
import com.example.restaurantmanagement.manager.Boundary.Coupon.CreateCouponView;
import com.example.restaurantmanagement.staff.Controller.ListUnfulfilledOrders;

import java.util.ArrayList;

public class ManageOrderView extends AppCompatActivity {
    // create delete food menu item function
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        Button viewUnfulfilled = findViewById(R.id.viewUnfulfilled);
        Button viewFulfilled = findViewById(R.id.viewFulfilled);
        RecyclerView manageOrderRV = findViewById(R.id.idRVManageOrderManager);
        ListUnfulfilledOrders listUnfulfilledOrders = new ListUnfulfilledOrders(ManageOrderView.this);
        ArrayList<Integer> orderObjects;
        orderObjects = listUnfulfilledOrders.showFulFilled();
        System.out.println(orderObjects + "Integer list");

        // we are initializing our adapter class and passing our arraylist to it.
        ManageOrderViewHolder manageOrderAdapter = new ManageOrderViewHolder(ManageOrderView.this, orderObjects);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // in below two lines we are setting layoutManager and adapter to our recycler view.
        manageOrderRV.setLayoutManager(linearLayoutManager);
        manageOrderRV.setAdapter(manageOrderAdapter);

        viewUnfulfilled.setOnClickListener(v -> {
            //load the arraylist with orders that is unfulfilled
            //notify changes
        });

        viewFulfilled.setOnClickListener(v -> {
            //load the arraylist with orders that is fulfilled
            //notify changes
        });

    }
}
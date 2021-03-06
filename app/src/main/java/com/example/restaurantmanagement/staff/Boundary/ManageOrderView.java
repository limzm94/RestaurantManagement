package com.example.restaurantmanagement.staff.Boundary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.staff.Controller.ListFulfilledOrders;
import com.example.restaurantmanagement.staff.Controller.ListUnfulfilledOrders;

import java.util.ArrayList;

public class ManageOrderView extends AppCompatActivity {
    // create delete food menu item function
    private final ArrayList<Integer> orderObjects = new ArrayList<>();
    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        Button viewUnfulfilled = findViewById(R.id.viewUnfulfilled);
        Button viewFulfilled = findViewById(R.id.viewFulfilled);
        RecyclerView manageOrderRV = findViewById(R.id.idRVManageOrderManager);
        ListUnfulfilledOrders listUnfulfilledOrders = new ListUnfulfilledOrders(ManageOrderView.this);
        ListFulfilledOrders listFulfilledOrders = new ListFulfilledOrders(ManageOrderView.this);

        orderObjects.addAll(listUnfulfilledOrders.showFulFilled());
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
            orderObjects.clear();
            orderObjects.addAll(listUnfulfilledOrders.showFulFilled());
            //notify changes
            manageOrderAdapter.notifyDataSetChanged();
        });

        viewFulfilled.setOnClickListener(v -> {
            //load the arraylist with orders that is fulfilled
            orderObjects.clear();
            orderObjects.addAll(listFulfilledOrders.showFulFilled());
            //notify changes
            manageOrderAdapter.notifyDataSetChanged();
        });

    }
}
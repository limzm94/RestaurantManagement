package com.example.restaurantmanagement.owner.Boundary;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.owner.Controller.ViewAnalytics;
import com.example.restaurantmanagement.R;

import java.util.ArrayList;


public class OwnerView extends AppCompatActivity {
    String searchRequirement = "";
    private ArrayList<OrderObject> userAccList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewAnalytics viewAnalytics = new ViewAnalytics(this);
        setContentView(R.layout.activity_analytics);

        EditText searchText = findViewById(R.id.search_date);
        Button searchBtn = findViewById(R.id.searchDateBtn);
        TextView summaryText = findViewById(R.id.analyticsTextView);
//        boolean yay = viewAnalytics.forTesting();
//        System.out.print(yay);

        searchBtn.setOnClickListener(v -> {
            searchRequirement = searchText.getText().toString();
            userAccList.addAll(viewAnalytics.getAllAnalytics(searchRequirement));

            System.out.print(userAccList);
            System.out.print("YOOOOOOOOOOOOOOOOOOOO");

            StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
            for(OrderObject customerEntity : userAccList) {
                System.out.println("Food name: "+ customerEntity.getFoodName());
                System.out.println("Is order fulfilled: "+ customerEntity.getIsFulfilled());
                System.out.println("Order Date: "+ customerEntity.getOrderDate());
            }

            summaryText.setTypeface(Typeface.MONOSPACE);
            summaryText.setText(cartSummary);

//            viewAnalytics
//            userAccList.clear();
//            userAccList.addAll(viewUser.showUser(searchRequirement));
//            adminAdapter.notifyDataSetChanged();
            System.out.println("Search button clicked");
        });
    }
}
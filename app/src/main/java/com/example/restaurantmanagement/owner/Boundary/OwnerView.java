package com.example.restaurantmanagement.owner.Boundary;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.restaurantmanagement.owner.Controller.ViewAnalytics;
import com.example.restaurantmanagement.R;


public class OwnerView extends AppCompatActivity {
    String searchRequirement = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewAnalytics viewAnalytics = new ViewAnalytics(this);
        setContentView(R.layout.activity_analytics);

        EditText searchText = findViewById(R.id.search_date);
        Button searchBtn = findViewById(R.id.searchDateBtn);
//        boolean yay = viewAnalytics.forTesting();
//        System.out.print(yay);

        searchBtn.setOnClickListener(v -> {
            searchRequirement = searchText.getText().toString();
//            userAccList.clear();
//            userAccList.addAll(viewUser.showUser(searchRequirement));
//            adminAdapter.notifyDataSetChanged();
            System.out.println("Search button clicked");
        });
    }
}
package com.example.restaurantmanagement.customer;


import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;

public class CheckoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        String checkOutSummary = getIntent().getStringExtra("key");
        TextView summaryText = findViewById(R.id.orderSummary);
        summaryText.setTypeface(Typeface.MONOSPACE);
        summaryText.setText(checkOutSummary);
        //set onClick for checkout Cart to submit the order
        //need to get the latest order number and then append it
    }
}

package com.example.restaurantmanagement.customer;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        String checkOutSummary = getIntent().getStringExtra("key");
        TextView summaryText = findViewById(R.id.orderSummary);


        ArrayList<OrderEntity> foodList = getIntent().getParcelableExtra("foodList");
        double totalCharge = 0.00;
        StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
        for(OrderEntity customerEntity : foodList) {
            System.out.println("Food name: "+ customerEntity.getFoodName());
            System.out.println("Food quantity: "+ customerEntity.getQuantity());  // Will invoke override `toString()` method
            totalCharge += (customerEntity.getPrice() * customerEntity.getQuantity());
            System.out.println("Total Charge: " + totalCharge);

            cartSummary.append(String.format("%-13s %-3d $%-5.2f $%-8.2f %n", customerEntity.getFoodName(), customerEntity.getQuantity(), customerEntity.getPrice(),
                    customerEntity.getQuantity() * customerEntity.getPrice()));
        }
        cartSummary.append(String.format("Total: $%.2f", totalCharge));
        summaryText.setTypeface(Typeface.MONOSPACE);
        summaryText.setText(checkOutSummary);
        //set onClick for checkout Cart to submit the order
        //need to get the latest order number and then append it
    }
}

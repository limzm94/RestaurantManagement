package com.example.restaurantmanagement.customer.Boundary;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Controller.LatestOrderId;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        LatestOrderId latestOrderId = new LatestOrderId(CheckoutActivity.this);
        //String checkOutSummary = getIntent().getStringExtra("key");
        TextView summaryText = findViewById(R.id.orderSummary);
        Bundle bundle =  getIntent().getExtras();
        ArrayList<OrderObject> foodList;

        foodList = (ArrayList<OrderObject>)getIntent().getSerializableExtra("foodList");
        double totalCharge = 0.00;
        int discount = 0;
        String customerName = "";
        StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
        for(OrderObject customerEntity : foodList) {
            System.out.println("Food name: "+ customerEntity.getFoodName());
            System.out.println("Food quantity: "+ customerEntity.getQuantity());  // Will invoke override `toString()` method
            totalCharge += (customerEntity.getPrice() * customerEntity.getQuantity());
            System.out.println("Total Charge: " + totalCharge);
            discount = customerEntity.getDiscount();
            customerName = customerEntity.getCustomerName();
            cartSummary.append(String.format("%-13s %-3d $%-5.2f $%-8.2f %n", customerEntity.getFoodName(), customerEntity.getQuantity(), customerEntity.getPrice(),
                    customerEntity.getQuantity() * customerEntity.getPrice()));
        }
        cartSummary.append(String.format("Total: $%.2f %n", totalCharge));
        cartSummary.append(String.format("Discount: %d%%%n", discount));
        cartSummary.append(String.format("Customer name: %s", customerName));
        summaryText.setTypeface(Typeface.MONOSPACE);
        summaryText.setText(cartSummary);
        //set onClick for checkout Cart to submit the order
        //need to get the latest order number and then append it
    }
}

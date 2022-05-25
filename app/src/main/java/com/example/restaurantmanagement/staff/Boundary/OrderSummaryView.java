package com.example.restaurantmanagement.staff.Boundary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Boundary.CustomerView;
import com.example.restaurantmanagement.customer.Controller.SendOrder;
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.staff.Controller.ViewOrderSummary;

import java.util.ArrayList;

public class OrderSummaryView extends AppCompatActivity {
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        TextView summaryText = findViewById(R.id.orderSummary);
        ViewOrderSummary viewOrderSummary = new ViewOrderSummary(OrderSummaryView.this);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        ArrayList<OrderObject> orderList;

        int orderId = getIntent().getIntExtra("orderId", 0);
        orderList = viewOrderSummary.showOrderSummary(orderId);
        //get the full order from the database

        double totalCharge = 0.00;
        int discount = 0;
        String customerName = "";
        StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
        for(OrderObject customerEntity : orderList) {
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
        cartSummary.append(String.format("Total after discount: $%.2f %n", totalCharge * (100 - discount) / 100 ));
        cartSummary.append(String.format("Customer username: %s%n", customerName));
        cartSummary.append(String.format("Order ID: %d%n", orderId));
        summaryText.setTypeface(Typeface.MONOSPACE);
        summaryText.setText(cartSummary);

        cancelBtn.setOnClickListener(v -> finish());
    }
}

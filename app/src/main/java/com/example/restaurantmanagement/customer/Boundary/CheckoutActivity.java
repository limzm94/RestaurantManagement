package com.example.restaurantmanagement.customer.Boundary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Controller.LatestOrderId;
import com.example.restaurantmanagement.customer.Controller.SendOrder;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        SendOrder sendOrder = new SendOrder(CheckoutActivity.this);
        TextView summaryText = findViewById(R.id.orderSummary);
        Button submitOrderBtn = findViewById(R.id.confirmBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        ArrayList<OrderObject> orderList;
        String customerName = getIntent().getStringExtra("customerName");
        String role = getIntent().getStringExtra("accountRole");

        orderList = (ArrayList<OrderObject>)getIntent().getSerializableExtra("foodList");
        double totalCharge = 0.00;
        int discount = 0;
        StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
        for(OrderObject customerEntity : orderList) {
            System.out.println("Food name: "+ customerEntity.getFoodName());
            System.out.println("Food quantity: "+ customerEntity.getQuantity());  // Will invoke override `toString()` method
            totalCharge += (customerEntity.getPrice() * customerEntity.getQuantity());
            System.out.println("Total Charge: " + totalCharge);
            discount = customerEntity.getDiscount();
            cartSummary.append(String.format("%-13s %-3d $%-5.2f $%-8.2f %n", customerEntity.getFoodName(), customerEntity.getQuantity(), customerEntity.getPrice(),
                    customerEntity.getQuantity() * customerEntity.getPrice()));
        }
        cartSummary.append(String.format("Total: $%.2f %n", totalCharge));
        cartSummary.append(String.format("Discount: %d%%%n", discount));
        cartSummary.append(String.format("Total after discount: $%.2f %n", totalCharge * (100 - discount) / 100 ));


        summaryText.setTypeface(Typeface.MONOSPACE);
        summaryText.setText(cartSummary);

        submitOrderBtn.setOnClickListener(v -> {
            sendOrder.submitOrder(orderList);
            Intent intent = new Intent(this, CustomerView.class);
            intent.putExtra("accountRole",role);
            intent.putExtra("customerName",customerName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        cancelBtn.setOnClickListener(v -> finish());
    }
}

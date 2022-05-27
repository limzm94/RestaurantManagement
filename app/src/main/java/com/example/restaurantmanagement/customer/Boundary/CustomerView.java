package com.example.restaurantmanagement.customer.Boundary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Controller.ChangeCustomer;
import com.example.restaurantmanagement.customer.Controller.CheckCustomer;
import com.example.restaurantmanagement.customer.Controller.CouponDiscount;
import com.example.restaurantmanagement.customer.Controller.ShowMenu;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {
    private int orderQuantity = 0;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ShowMenu showMenu = new ShowMenu(CustomerView.this);
        CouponDiscount couponDiscount = new CouponDiscount(CustomerView.this);
        ChangeCustomer changeCustomer = new ChangeCustomer(CustomerView.this);
        CheckCustomer checkCustomer = new CheckCustomer(CustomerView.this);
        EditText customerNameText = findViewById(R.id.customerName);
        Button checkOutBtn = findViewById(R.id.checkOutBtn);
        Button logOutBtn = findViewById(R.id.logout_btn);
        Button couponBtn = findViewById(R.id.couponBtn);
        RecyclerView adminRV = findViewById(R.id.idRVCustomer);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String customerName = getIntent().getStringExtra("customerName");
        String role = getIntent().getStringExtra("accountRole");


        if (role.equals("Owner") || role.equals("Staff")) {
            logOutBtn.setVisibility(View.GONE);
        } else {
            logOutBtn.setVisibility(View.VISIBLE);
        }

        ArrayList<OrderObject> orderList;
        orderList = showMenu.displayMenu();
        // we are initializing our adapter class and passing our arraylist to it.
        CustomerViewHolder foodAdapter = new CustomerViewHolder(orderList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(foodAdapter);



        checkOutBtn.setOnClickListener(v -> {
            for(OrderObject orderObject : orderList) {
                orderQuantity = orderQuantity + orderObject.getQuantity();
            }
            if (customerNameText.getText().toString().equals("")) {
                Toast.makeText(CustomerView.this, "Please enter a username", Toast.LENGTH_LONG).show();
            } else if (orderQuantity == 0) {
                Toast.makeText(CustomerView.this, "Please add item to cart", Toast.LENGTH_LONG).show();}
            else if (!checkCustomer.checkCustomer(customerNameText.getText().toString())) {
                    Toast.makeText(CustomerView.this, "Invalid username", Toast.LENGTH_LONG).show();
            } else {
                changeCustomer.changeCustomer(orderList, customerNameText.getText().toString());
                Intent cartCheckOut = new Intent(CustomerView.this, CheckoutView.class);
                cartCheckOut.putExtra("foodList", orderList);
                cartCheckOut.putExtra("accountRole", role);
                cartCheckOut.putExtra("customerName", customerNameText.getText().toString());
                startActivity(cartCheckOut);
            }
        });

        // need to check the coupon
        couponBtn.setOnClickListener(v -> {
            final EditText couponInput = new EditText(this);
            couponInput.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(couponInput)
                    .setPositiveButton("Confirm", (dialog, id) -> {
                                if (couponDiscount.couponDiscount(orderList, couponInput.getText().toString())) {
                                    Toast.makeText(CustomerView.this, "Coupon authorized", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(CustomerView.this, "Invalid Coupon", Toast.LENGTH_LONG).show();
                                }
                            }
                    )
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel()
                    );
            AlertDialog alert = builder.create();
            alert.setTitle("Enter Coupon Code");
            alert.show();
        });

        if (role.equals("Customer")) {
            customerNameText.setText(customerName);
            customerNameText.setEnabled(false);
        } else {
            customerNameText.setHint("Input username");
        }

        logOutBtn.setOnClickListener(v -> finish());
    }
}
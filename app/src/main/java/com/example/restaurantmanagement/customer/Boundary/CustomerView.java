package com.example.restaurantmanagement.customer.Boundary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.customer.Controller.ChangeCustomer;
import com.example.restaurantmanagement.customer.Controller.CouponDiscount;
import com.example.restaurantmanagement.customer.Controller.ShowMenu;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ShowMenu showMenu = new ShowMenu(CustomerView.this);
        CouponDiscount couponDiscount = new CouponDiscount(CustomerView.this);
        ChangeCustomer changeCustomer = new ChangeCustomer(CustomerView.this);
        EditText customerNameText = findViewById(R.id.customerName);
        Button changeCustomerBtn = findViewById(R.id.changeCustomerBtn);
        Button checkOutBtn = findViewById(R.id.checkOutBtn);
        Button logOutBtn = findViewById(R.id.logout_btn);
        Button couponBtn = findViewById(R.id.couponBtn);
        RecyclerView adminRV = findViewById(R.id.idRVCustomer);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ArrayList<OrderObject> orderList;
        orderList = showMenu.displayMenu();
        customerNameText.setText("No Customer Selected");
        // we are initializing our adapter class and passing our arraylist to it.
        CustomerViewHolder foodAdapter = new CustomerViewHolder(orderList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(foodAdapter);

        checkOutBtn.setOnClickListener(v -> {
            Intent cartCheckOut = new Intent(CustomerView.this, CheckoutActivity.class);
            cartCheckOut.putExtra("foodList", orderList);
            startActivity(cartCheckOut);
        });

        // need to check the coupon
        couponBtn.setOnClickListener(v -> {
            final EditText couponInput = new EditText(this);
            couponInput.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(couponInput)
                    .setPositiveButton("Confirm", (dialog, id) ->{
                        if (couponDiscount.couponDiscount(orderList ,couponInput.getText().toString())) {
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

        changeCustomerBtn.setOnClickListener(v -> {
            String customerName = customerNameText.getText().toString();
            if (changeCustomer.changeCustomer(orderList, customerName)) {
                Toast.makeText(CustomerView.this, "Customer changed", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CustomerView.this, "Customer record not found", Toast.LENGTH_LONG).show();
            }
        });
        logOutBtn.setOnClickListener(v -> finish());
    }
}
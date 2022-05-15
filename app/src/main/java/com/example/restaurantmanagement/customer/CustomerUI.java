package com.example.restaurantmanagement.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.MainActivity;
import com.example.restaurantmanagement.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerUI extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Button checkOutBtn = findViewById(R.id.checkOutBtn);
        Button couponBtn = findViewById(R.id.couponBtn);
        RecyclerView adminRV = findViewById(R.id.idRVCustomer);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);


        // here we have created new array list and added data to it.
        // Arraylist for storing data
        ArrayList<CustomerEntity> userAccList = new ArrayList<>();
        userAccList.add(new CustomerEntity("Curry","A curry is a dish with a sauce seasoned with spices, mainly associated with South Asian cuisine.", 4.50, 0, "Table 1"));
        userAccList.add(new CustomerEntity("Chicken Rice","Hainan's chicken rice is a dish of poached chicken and seasoned rice, served with chilli sauce and usually with cucumber garnishes.", 3.50, 0, "Table 1"));
        // we are initializing our adapter class and passing our arraylist to it.
        CustomerController courseAdapter = new CustomerController(userAccList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(courseAdapter);

        checkOutBtn.setOnClickListener(v -> {
            double totalCharge = 0.00;
            StringBuilder cartSummary = new StringBuilder((String.format("%-13s %-3s %-5s %-8s %n", "Item", "Qty", "Price", "Subtotal")));
            for(CustomerEntity customerEntity : userAccList) {
                System.out.println("Food name: "+ customerEntity.getFoodName());
                System.out.println("Food quantity: "+ customerEntity.getQuantity());  // Will invoke override `toString()` method
                totalCharge += (customerEntity.getPrice() * customerEntity.getQuantity());
                System.out.println("Total Charge: " + totalCharge);

                cartSummary.append(String.format("%-13s %-3d $%-5.2f $%-8.2f %n", customerEntity.getFoodName(), customerEntity.getQuantity(), customerEntity.getPrice(),
                        customerEntity.getQuantity() * customerEntity.getPrice()));
            }
            cartSummary.append(String.format("Total: $%.2f", totalCharge));

            Intent cartCheckOut = new Intent(CustomerUI.this, CheckoutActivity.class);
            cartCheckOut.putExtra("key", cartSummary.toString());
            startActivity(cartCheckOut);
        });
        // set editText


        couponBtn.setOnClickListener(v -> {
            final EditText couponInput = new EditText(this);
            couponInput.setInputType(InputType.TYPE_CLASS_TEXT);
            /// button click event
            //Setting message manually and performing action on button click
            builder2.setView(couponInput)
                    .setPositiveButton("Confirm", (dialog, id) ->
                                    System.out.println("Send Coupon")
                            //if coupon return true, implement discount
                    )
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel()
                    );
            //Creating dialog box
            AlertDialog alert = builder2.create();
            //Setting the title manually
            alert.setTitle("Enter Coupon Code");
            alert.show();
        });


    }
}
package com.example.restaurantmanagement.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.manager.ManagerController;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class CustomerUI extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        TextView customerName = findViewById(R.id.customerName);
        Button changeCustomerBtn = findViewById(R.id.changeCustomerBtn);
        Button checkOutBtn = findViewById(R.id.checkOutBtn);
        Button couponBtn = findViewById(R.id.couponBtn);
        RecyclerView adminRV = findViewById(R.id.idRVCustomer);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);


        //Preload all the menu
        ManagerController managerController = new ManagerController();
        managerController.insertFood(CustomerUI.this,"Curry","Curry is a dish with a sauce seasoned with spices.", 7.50);
        managerController.insertFood(CustomerUI.this,"Chicken Rice","Hainan's chicken rice is a dish of poached chicken and seasoned rice.", 4.50);
        managerController.insertFood(CustomerUI.this,"Ramen","Ramen is a Japanese noodle dish.", 10.90);
        managerController.insertFood(CustomerUI.this,"Bingsu","Bingsu is a Korean shaved ice dessert with sweet toppings.", 8.00);
        managerController.insertFood(CustomerUI.this,"Carrot Cake","Carrot cake is cake that contains carrots mixed into the batter.", 6.50);


        // need to do this in controller
        DBHandler DB = new DBHandler(this);
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<String> allFoodName = DB.listColumnsDataStr("foods", "name");
        System.out.println(allFoodName);
        ArrayList<String> allFoodDesc = DB.listColumnsDataStr("foods", "description");
        System.out.println(allFoodDesc);
        ArrayList<Double> allPrice = DB.listColumnsDataDbl("foods", "price");
        System.out.println(allPrice);



// need to do this in controller
        ArrayList<OrderEntity> foodList = new ArrayList<>();
        int count = 0;
        while (allFoodName.size() > count) {
            foodList.add(new OrderEntity(allFoodName.get(count), allFoodDesc.get(count), allPrice.get(count), 0, "Customer Name"));
            count++;
        }

        // here we have created new array list and added data to it.
        // Arraylist for storing data

        //userAccList.add(new OrderEntity("Curry","A curry is a dish with a sauce seasoned with spices, mainly associated with South Asian cuisine.", 4.50, 0, "Table 1"));
        //userAccList.add(new OrderEntity("Chicken Rice","Hainan's chicken rice is a dish of poached chicken and seasoned rice, served with chilli sauce and usually with cucumber garnishes.", 3.50, 0, "Table 1"));
        // we are initializing our adapter class and passing our arraylist to it.
        CustomerController foodAdapter = new CustomerController(foodList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(foodAdapter);

        checkOutBtn.setOnClickListener(v -> {
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


        customerName.setText("No Customer Selected");
        couponBtn.setOnClickListener(v -> {

            // need to query the database for account name that roles that is customer

            final EditText couponInput = new EditText(this);
            couponInput.setInputType(InputType.TYPE_CLASS_TEXT);
            /// button click event
            //Setting message manually and performing action on button click
            builder.setView(couponInput)
                    .setPositiveButton("Confirm", (dialog, id) ->
                                    System.out.println("Send Coupon")
                            //if coupon return true, implement discount
                    )
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel()
                    );
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Enter Coupon Code");
            alert.show();
        });


    }
}
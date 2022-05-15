package com.example.restaurantmanagement.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.owner.OwnerUI;
import com.example.restaurantmanagement.utility.DBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class AdminUI extends AppCompatActivity {
    private RecyclerView adminRV;
    // Arraylist for storing data
    private ArrayList<AdminEntity> userAccList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button createAccBtn = findViewById(R.id.createAcc);
        adminRV = findViewById(R.id.idRVAdmin);
        FloatingActionButton homeFab = findViewById(R.id.homeFAB);



        // need to do this in controller
        DBHandler DB = new DBHandler(this);
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<String> allUsername = DB.listColumnsData("users", "username");
        System.out.println(allUsername);
        ArrayList<String> allPassword = DB.listColumnsData("users", "password");
        System.out.println(allPassword);
        ArrayList<String> allPersonName = DB.listColumnsData("users", "name");
        System.out.println(allPersonName);
        ArrayList<String> allStatus = DB.listColumnsData("users", "status");
        System.out.println(allStatus);
        ArrayList<String> allRoles = DB.listColumnsData("users", "role");
        System.out.println(allRoles);

        // need to do this in controller
        userAccList = new ArrayList<>();
        int count = 0;
        while (allUsername.size() > count) {
            userAccList.add(new AdminEntity(allPersonName.get(count), allStatus.get(count), allRoles.get(count), allUsername.get(count), allPassword.get(count)));
            count++;
        }

        // we are initializing our adapter class and passing our arraylist to it.
        AdminController courseAdapter = new AdminController(this, userAccList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(courseAdapter);

        createAccBtn.setOnClickListener(v -> {
            Intent createAcc = new Intent(AdminUI.this, CreateAccount.class);
            startActivity(createAcc);
            finish();
        });


        // need to check if owner log in to show the button
        homeFab.setOnClickListener(v -> {
            Intent createAcc = new Intent(AdminUI.this, OwnerUI.class);
            startActivity(createAcc);
            finish();
        });

    }
}

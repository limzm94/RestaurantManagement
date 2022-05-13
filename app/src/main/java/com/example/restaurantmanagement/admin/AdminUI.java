package com.example.restaurantmanagement.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.MainActivity;
import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.SignUpActivity;
import com.example.restaurantmanagement.utility.DBHandler;


import java.util.ArrayList;
import java.util.Arrays;

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
        DBHandler DB = new DBHandler(this);

        // here we have created new array list and added data to it.
        ArrayList<String> allUser = DB.listUser();
        System.out.println(allUser);
        System.out.println(Arrays.toString(allUser.toArray()));


        userAccList = new ArrayList<>();
        //userAccList.add(new AdminEntity("John","Active", "Manager", "john_manager", "nd91123912"));
        /*userAccList.add(new AdminEntity("John","Active", "Manager", "john_manager", "nd91123912"));
        userAccList.add(new AdminEntity("Jane","Active", "Owner", "jane_owner", "23948fcxv"));
        userAccList.add(new AdminEntity("Table 1","Active", "Customer Table", "customer_table1", "table1"));*/
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
        });

    }
}

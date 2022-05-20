package com.example.restaurantmanagement.admin.Boundary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.admin.Controller.ViewUser;
import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.owner.OwnerUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {
    private RecyclerView adminRV;
    // Arraylist for storing data
    private ArrayList<UserObject> userAccList = new ArrayList<>();
    String searchRequirement = "";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ViewUser viewUser = new ViewUser(AdminPage.this);
        EditText searchText = findViewById(R.id.search_text);
        Button createAccBtn = findViewById(R.id.createAcc);
        Button logoutBtn = findViewById(R.id.adminLogOut);
        Button searchBtn = findViewById(R.id.searchBtn);
        adminRV = findViewById(R.id.idRVAdmin);
        FloatingActionButton homeFab = findViewById(R.id.homeFAB);

        userAccList.addAll(viewUser.showUser(""));
        // we are initializing our adapter class and passing our arraylist to it.
        AdminViewHolder adminAdapter = new AdminViewHolder(this, userAccList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutManager and adapter to our recycler view.
        adminRV.setLayoutManager(linearLayoutManager);
        adminRV.setAdapter(adminAdapter);


        createAccBtn.setOnClickListener(v -> {
            Intent createAcc = new Intent(AdminPage.this, CreateAccount.class);
            startActivity(createAcc);
            finish();
        });


        // need to check if owner log in to show the button
        homeFab.setOnClickListener(v -> {
            Intent createAcc = new Intent(AdminPage.this, OwnerUI.class);
            startActivity(createAcc);
            finish();
        });

        searchBtn.setOnClickListener(v -> {
            searchRequirement = searchText.getText().toString();
            userAccList.clear();
            userAccList.addAll(viewUser.showUser(searchRequirement));
            // in below two lines we are setting layoutManager and adapter to our recycler view.
            System.out.println(userAccList);
            adminAdapter.notifyDataSetChanged();
            System.out.println("Search button clicked");
        });

        logoutBtn.setOnClickListener(v -> finish());
    }
}

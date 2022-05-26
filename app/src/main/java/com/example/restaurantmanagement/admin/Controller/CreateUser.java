package com.example.restaurantmanagement.admin.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CreateUser {
    Context context;

    public CreateUser(Context context) {
        this.context = context;
    }

    public void createAcc(String username, String password, String status, String personName, String role){
        UserEntity userEntity = new UserEntity(context);

        if (!userEntity.checkUser(username)) {
            if (userEntity.insertUser(username, password, status, personName, role)) {
                Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "User already exists!", Toast.LENGTH_SHORT).show();
        }
    }
}

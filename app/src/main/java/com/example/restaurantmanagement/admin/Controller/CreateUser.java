package com.example.restaurantmanagement.admin.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CreateUser {
    Context context;

    public CreateUser(Context context) {
        this.context = context;
    }

    public boolean createAcc(String username, String password, String status, String personName, String role){
        UserEntity userEntity = new UserEntity(context);
        return userEntity.insertUser(username, password, status, personName, role);
    }
}

package com.example.restaurantmanagement.login.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CheckRole {
    Context context;

    public CheckRole(Context context) {
        this.context = context;
    }

    public String checkRoles(String username, String password) {
        UserEntity userEntity = new UserEntity(context);
        return userEntity.checkRole(username, password);
    }
}
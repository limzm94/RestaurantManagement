package com.example.restaurantmanagement.login.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CheckLogin {
    private final Context context;

    public CheckLogin(Context context) {
        this.context = context;
    }

    public boolean checkUserAndPass(String username, String password) {
        UserEntity userEntity = new UserEntity(context);
        return userEntity.checkUsernameAndPassword(username, password);
    }
}
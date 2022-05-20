package com.example.restaurantmanagement.login.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CheckIsActive {
    Context context;

    public CheckIsActive(Context context) {
        this.context = context;
    }

    public boolean checkStatus(String username, String password) {
        UserEntity userEntity = new UserEntity(context);
        return userEntity.checkIsActive(username, password);
    }
}
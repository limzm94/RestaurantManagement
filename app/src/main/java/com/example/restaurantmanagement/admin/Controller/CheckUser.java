package com.example.restaurantmanagement.admin.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class CheckUser {
    Context context;

    public CheckUser(Context context) {
        this.context = context;
    }

    public boolean checkAcc(String username){
        UserEntity userEntity = new UserEntity(context);
        return userEntity.checkUser(username);
    }
}

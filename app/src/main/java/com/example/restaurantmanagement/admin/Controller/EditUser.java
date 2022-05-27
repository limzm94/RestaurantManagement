package com.example.restaurantmanagement.admin.Controller;


import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;

public class EditUser {
    private final Context context;

    public EditUser(Context context) {
        this.context = context;
    }

    public void updateAcc(String username, String password, String personName, String status, String role, int userKey){
        UserEntity userEntity = new UserEntity(context);
        userEntity.updateUser(username, password, personName, status, role, userKey);
    }
}

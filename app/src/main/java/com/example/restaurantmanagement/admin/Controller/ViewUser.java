package com.example.restaurantmanagement.admin.Controller;

import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserEntity;
import com.example.restaurantmanagement.admin.Entity.UserObject;

import java.util.ArrayList;

public class ViewUser {
    Context context;

    public ViewUser(Context context) {
        this.context = context;
    }

    public ArrayList<UserObject> showUser(String searchRequirement){
        UserEntity userEntity = new UserEntity(context);
        return userEntity.listUsers(searchRequirement);
    }
}

package com.example.restaurantmanagement.owner.Entity;
import android.content.Context;

import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;
public class AnalyticsEntity {
    Context context;

    public AnalyticsEntity(Context context) {
        this.context = context;
    }

    public boolean insertAnalytics() {
        DBHandler DB = new DBHandler(context);
        return DB.insertOrderDetails("food", "21-03-1999",29.9,29.9,1,"Jade", "0",1,2,0);
    }
}

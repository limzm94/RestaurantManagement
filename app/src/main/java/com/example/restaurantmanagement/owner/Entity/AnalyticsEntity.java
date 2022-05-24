package com.example.restaurantmanagement.owner.Entity;
import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.FoodObject;
import com.example.restaurantmanagement.customer.Entity.OrderObject;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;
public class AnalyticsEntity {
    Context context;

    public AnalyticsEntity(Context context) {
        this.context = context;
    }


    public ArrayList<OrderObject> getSpendingAnalytics(){
        DBHandler DB = new DBHandler(context);
        return DB.getSpendingHabit();
    }


    // earnings
    public int getDailyEarnings(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getDailyEarnings(date);
    }

    public int getMonthlyEarnings(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getMonthlyEarnings(date);
    }

    public int getYearlyEarnings(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getYearlyEarnings(date);
    }

    // food preference

    public String getTdyFoodPreference(String date) {
        DBHandler DB = new DBHandler(context);
        return DB.getPreferenceFood(date);
    }

    public String getMthFoodPreference(String date) {
        DBHandler DB = new DBHandler(context);
        return DB.getMonthPreferenceFood(date);
    }

    public String getYearFoodPreference(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getYearPreferenceFood(date);
    }

    // frequency of visit

    public int getFrqVisit(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getFrequencyVisit(date);
    }

    public int getFrqVisitMonth(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getFrequencyVisitByMonth(date);
    }

    public int getFrqVisitYear(String date){
        DBHandler DB = new DBHandler(context);
        return DB.getFrequencyVisitByYear(date);
    }




}

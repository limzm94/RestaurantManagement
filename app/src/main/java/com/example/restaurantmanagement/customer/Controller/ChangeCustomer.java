package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class ChangeCustomer {
    private final Context context;

    public ChangeCustomer(Context context) {
        this.context = context;
    }

    public boolean changeCustomer(ArrayList<OrderObject> orderList, String customerName) {
        CheckCustomer checkCustomer = new CheckCustomer(context);
        if (checkCustomer.checkCustomer(customerName)) {
            int count = 0;
            while (orderList.size() > count) {
                orderList.get(count).setCustomerName(customerName);
                count++ ;
            }
            return true;
        }
        else
            return false;
    }
}

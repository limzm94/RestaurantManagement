package com.example.restaurantmanagement.customer.Entity;


import android.content.Context;

import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.manager.Entity.CouponObject;
import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class OrderEntity {
    private final Context context;

    public OrderEntity(Context context) {
        this.context = context;
    }

    public ArrayList<OrderObject> populateMenu() {
        // need to do this in controller
        DBHandler DB = new DBHandler(context);
        ArrayList<OrderObject> menuList = new ArrayList<>();
        // here we have created new array list and added data to it. System print for debugging purpose
        ArrayList<Integer> allFoodKey = DB.listColumnsDataInt("foods", "menuId");
        System.out.println(allFoodKey);
        ArrayList<String> allFoodName = DB.listColumnsDataStr("foods", "name");
        System.out.println(allFoodName);
        ArrayList<String> allFoodDesc = DB.listColumnsDataStr("foods", "description");
        System.out.println(allFoodDesc);
        ArrayList<Double> allPrice = DB.listColumnsDataDbl("foods", "price");
        System.out.println(allPrice);
        int count = 0;
        while (allFoodName.size() > count) {
            menuList.add(new OrderObject(allFoodName.get(count), allFoodDesc.get(count), allPrice.get(count),
                    0, "Customer Name", "Unfulfilled", 0, allFoodKey.get(count), 0, ""));
            count++;
        }
        return menuList;
    }

    public Boolean checkCustomer(String customerName) {
        DBHandler DB = new DBHandler(context);
        return DB.checkCustomer(customerName);
    }

    public Boolean checkCouponValidity(String couponCode) {
        DBHandler DB = new DBHandler(context);
        return DB.checkCouponValid(couponCode);
    }

    public int getCouponDiscount(String couponCode) {
        DBHandler DB = new DBHandler(context);
        return DB.getDiscount(couponCode);
    }

    public int getOrderId() {
        DBHandler DB = new DBHandler(context);
        return DB.getOrderID();
    }

    public void submitOrder(String foodName, String orderDate, double price, double discountedPrice, int quantity, String customerName,
                            String isFulfilled, int orderId, int foodKey, int discount) {
        DBHandler DB = new DBHandler(context);
        DB.insertOrderDetails(foodName, orderDate, price, discountedPrice,quantity, customerName, isFulfilled, orderId, foodKey, discount);
    }
}
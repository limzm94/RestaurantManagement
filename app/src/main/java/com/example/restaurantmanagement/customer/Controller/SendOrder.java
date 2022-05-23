package com.example.restaurantmanagement.customer.Controller;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SendOrder {
    Context context;

    public SendOrder(Context context) {
        this.context = context;
    }

    public void submitOrder(ArrayList<OrderObject> orderList) {
        OrderEntity orderEntity = new OrderEntity(context);
        LatestOrderId latestOrderId = new LatestOrderId(context);
        int orderId = latestOrderId.getOrderId() + 1; // get latest orderId from database and append it by 1
        // get date
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);

        for (OrderObject order : orderList) {
            orderEntity.submitOrder(order.getFoodName(), strDate, order.getPrice(), ((order.getPrice() * (100 - order.getDiscount()) / 100)), order.getQuantity(),
                    order.getCustomerName(), "Unfulfilled", orderId, order.getFoodKey(), order.getDiscount());
        }
    }
}

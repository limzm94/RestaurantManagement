package com.example.restaurantmanagement.customer.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.restaurantmanagement.customer.Entity.OrderEntity;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.util.ArrayList;

public class CouponDiscount {
    Context context;

    public CouponDiscount(Context context) {
        this.context = context;
    }

    public boolean couponDiscount(ArrayList<OrderObject> orderList, String couponCode) {
        OrderEntity orderEntity = new OrderEntity(context);
        CheckCouponValidity checkCouponValidity = new CheckCouponValidity(context);
        if (checkCouponValidity.couponValidity(couponCode)) {
            int count = 0;
            while (orderList.size() > count) {
                orderList.get(count).setDiscount(orderEntity.getCouponDiscount(couponCode));
                count++ ;
            }
            return true;
        }
        else
            return false;
    }
}

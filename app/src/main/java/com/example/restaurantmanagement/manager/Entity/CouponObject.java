package com.example.restaurantmanagement.manager.Entity;

public class CouponObject {

    private int couponKey, couponDisc ;
    private String couponCode, couponDesc, couponStatus;

    public CouponObject(String couponCode, String couponDesc, String couponStatus, int couponKey, int couponDisc) {
        this.couponCode = couponCode;
        this.couponDesc = couponDesc;
        this.couponStatus = couponStatus;
        this.couponKey = couponKey;
        this.couponDisc = couponDisc;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getCouponKey() {
        return couponKey;
    }

    public void setCouponKey(int couponKey) {
        this.couponKey = couponKey;
    }

    public int getCouponDisc() {
        return couponDisc;
    }

    public void setCouponDisc(int couponDisc) {
        this.couponDisc = couponDisc;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

}

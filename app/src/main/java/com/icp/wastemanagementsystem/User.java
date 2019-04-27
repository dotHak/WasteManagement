package com.icp.wastemanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class User {

    private String mUsername;
    private String mEmail;
    private int mCredit;
    private Map<Date, ArrayList> mBarcodeList;

    public User() {
    }

    public User(String username, String email, int credit) {
        mUsername = username;
        mEmail = email;
        mCredit = credit;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getCredit() {
        return mCredit;
    }

    public void setCredit(int credit) {
        mCredit = credit;
    }

    public Map<Date, ArrayList> getBarcodeList() {
        return mBarcodeList;
    }

    public void setBarcodeList(Map<Date, ArrayList> barcodeList) {
        mBarcodeList = barcodeList;
    }
}

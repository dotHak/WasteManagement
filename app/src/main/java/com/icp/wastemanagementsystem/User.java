package com.icp.wastemanagementsystem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class User {

    private String mUsername;
    private String mEmail;
    private int mCredit;
    private Map<String, List<String>> mBarcodeList;

    public User() {
    }

    public User(String username, String email, int credit) {
        mUsername = username;
        mEmail = email;
        mCredit = credit;
        mBarcodeList = new HashMap<>();
    }




    public void updateList(String barcode) {
        String today = DateFormat.getDateInstance(DateFormat.LONG, Locale.UK).format(new Date());
        if(mBarcodeList == null){
            mBarcodeList = new HashMap<>();
        }
        if (mBarcodeList.isEmpty()) {
            mBarcodeList.put(today, new ArrayList<String>());
            mBarcodeList.get(today).add(barcode);
            mCredit +=20;

        } else if (mBarcodeList.containsKey(today)) {
            mBarcodeList.get(today).add(barcode);
            mCredit += 20;
        } else {
            mBarcodeList.put(today, new ArrayList<String>());
            mBarcodeList.get(today).add(barcode);
            mCredit += 20;
        }
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


    public Map<String, List<String>> getBarcodeList() {
        return mBarcodeList;
    }

    public void setBarcodeList(Map<String, List<String>> barcodeList) {
        mBarcodeList = barcodeList;
    }
}

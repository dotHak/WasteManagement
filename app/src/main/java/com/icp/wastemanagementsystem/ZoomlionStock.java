package com.icp.wastemanagementsystem;

import java.util.ArrayList;

public class ZoomlionStock {
    private ArrayList<Stock> Zoomlion;
    private int numberofStocks;

    //default constructor
    public ZoomlionStock() {
    }
    //overloaded Constructor


    public ZoomlionStock(ArrayList<Stock> zoomlion, int numberofStocks) {
        Zoomlion = zoomlion;
        this.numberofStocks = numberofStocks;
    }

    //getters

    public ArrayList<Stock> getStock() {
        return Zoomlion;
    }

    public int getNumberofStocks() {
        return numberofStocks;
    }

    //setters

    public void setStock(ArrayList<Stock> zoomlion) {
        Zoomlion = zoomlion;
    }

    public void setNumberofStocks(int numberofStocks) {
        this.numberofStocks = numberofStocks;
    }

    //tostring

    @Override
    public String toString() {
        return "ZoomlionStock{" +
                "Zoomlion=" + Zoomlion +
                ", numberofStocks=" + numberofStocks +
                '}';
    }

    //get total quantity of stocks
    public int getTotalQuantity(ArrayList<Stock> Stocks) {
        int quantity = 0;
        for (Stock i : Stocks) {
            quantity += i.getQuantity();

        }
        return quantity;
    }
}

package com.icp.wastemanagementsystem;

import java.util.ArrayList;

public class AkornorStock {
    private ArrayList<Stock> Akornor;
    private int numberofStocks;

    //default constructor
    public AkornorStock() {
    }

    //overloaded constructor
    public AkornorStock(ArrayList<Stock> akornor, int numberofStocks) {
        Akornor = akornor;
        this.numberofStocks = numberofStocks;
    }

    //getters
    public ArrayList<Stock> getStock() {
        return Akornor;
    }

    public int getNumberofStocks() {
        return numberofStocks;
    }

    //setters
    public void setStock(ArrayList<Stock> akornor) {
        Akornor = akornor;
    }

    public void setNumberofStocks(int numberofStocks) {
        this.numberofStocks = numberofStocks;
    }


    @Override
    public String toString() {
        return "AkornorStock{" +
                "Akornor=" + Akornor +
                ", numberofStocks=" + numberofStocks +
                '}';
    }

    public int getTotalQuantity(ArrayList<Stock> Stocks) {
        int quantity = 0;
        for (Stock i : Stocks) {
            quantity += i.getQuantity();

        }
        return quantity;
    }

}

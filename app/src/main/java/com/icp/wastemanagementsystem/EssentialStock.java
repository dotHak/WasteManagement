package com.icp.wastemanagementsystem;

import java.util.ArrayList;

//A stock of Essential products
public class EssentialStock {
    private ArrayList<Stock> Essential;
    private int numberofStocks;

    //default constructor
    public EssentialStock(){}

    //overloaded Constructor
    public EssentialStock(ArrayList<Stock> essential, int numberofStocks) {
        Essential = essential;
        this.numberofStocks = numberofStocks;
    }

    //getters
    public ArrayList<Stock> getStock() {
        return Essential;
    }

    public int getNumberofStocks() {
        return numberofStocks;
    }

    //setters
    public void setStock(ArrayList<Stock> essential) {
        Essential = essential;
    }


    public void setNumberofStocks(int numberofStocks) {
        this.numberofStocks = numberofStocks;
    }
    //tostring method

    @Override
    public String toString() {
        return "EssentialStock{" +
                "Essential=" + Essential +
                ", numberofStocks=" + numberofStocks +
                '}';
    }

    public int getTotalQuantity(ArrayList<Stock> Stocks){
        int quantity = 0;
        for (Stock i: Stocks){
            quantity += i.getQuantity();

        }
        return quantity;
    }


}

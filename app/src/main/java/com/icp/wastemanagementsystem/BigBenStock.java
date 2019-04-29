package com.icp.wastemanagementsystem;

import java.util.ArrayList;

//this class holds all available stocks in Big Ben
public class BigBenStock {
    private ArrayList<Stock> BigBen;
    private int numberofStocks;

    //default constructor
    public BigBenStock() {
    }

    //overloaded constructor

    public BigBenStock(ArrayList<Stock> bigBen, int numberofStocks) {
        BigBen = bigBen;
        this.numberofStocks = numberofStocks;
    }

    //getters
    public ArrayList<Stock> getStock() {
        return BigBen;
    }

    public int getNumberofStocks() {
        return numberofStocks;
    }

    //setters
    public void setStock(ArrayList<Stock> bigBen) {
        BigBen = bigBen;
    }

    public void setNumberofStocks(int numberofStocks) {
        this.numberofStocks = numberofStocks;
    }

    public void removeStocks(int numer) {

    }

    @Override
    public String toString() {
        return "BigBenStock{" +
                "BigBen=" + BigBen +
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

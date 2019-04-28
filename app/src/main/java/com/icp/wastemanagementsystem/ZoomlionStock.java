package com.icp.wastemanagementsystem;

import java.util.ArrayList;

public class ZoomlionStock {
    private ArrayList<Stock> Zoomlion;
    private int numberofStocks;

    //default constructor
    public ZoomlionStock(){}
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
    public int getTotalQuantity(ArrayList<Stock> Stocks){
        int quantity = 0;
        for (Stock i: Stocks){
            quantity += i.getQuantity();

        }
        return quantity;
    }
    public static void main (String[] args){
        //services
        Stock stock1 = new Stock("Solid Waste Collection", "service", 10.00);
        Stock stock2 = new Stock("Public Cleaning", "service", 20.00);
        Stock stock3 = new Stock("Cesspit Emptying", "service", 30.00);
        Stock stock4 = new Stock("Fumigation", "service", 15.00);
        Stock stock5 = new Stock("Gardening", "service", 25.00);
        Stock stock6 = new Stock("window Cleaning", "service", 25.00);

        //products
        Stock stock7 = new Stock("Bins", "product", 10, 100);
        Stock stock8 = new Stock("Communal Container","product", 20, 50);

        ArrayList<Stock> ZoomlionStocks = new ArrayList<>();
        ZoomlionStocks.add(stock1);
        ZoomlionStocks.add(stock2);
        ZoomlionStocks.add(stock3);
        ZoomlionStocks.add(stock4);
        ZoomlionStocks.add(stock5);
        ZoomlionStocks.add(stock6);
        ZoomlionStocks.add(stock7);
        ZoomlionStocks.add(stock8);

        EssentialStock AppStock4 = new EssentialStock();
        AppStock4.setStock(ZoomlionStocks);


        AppStock4.setNumberofStocks(AppStock4.getTotalQuantity(ZoomlionStocks));

        System.out.println(AppStock4);

    }

}

package com.icp.wastemanagementsystem;

import java.util.ArrayList;

public class AkornorStock {
    private ArrayList<Stock> Akornor;
    private int numberofStocks;

    //default constructor
    public AkornorStock(){}

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
    public int getTotalQuantity(ArrayList<Stock> Stocks){
        int quantity = 0;
        for (Stock i: Stocks){
            quantity += i.getQuantity();

        }
        return quantity;
    }

    public static void main (String[] args){
        //meals
        //lunch and dinner
        Stock stock1 = new Stock("Fried Rice and Chicken", "deluxe meal",8.50, 20);
        Stock stock2 = new Stock("Jollof Rice and Chicken", "deluxe meal",8.50, 20);
        Stock stock3 = new Stock("Spaghetti and Chicken", "deluxe meal",8.50, 20);
        Stock stock4 = new Stock("Pasta and Chicken", "deluxe meal",8.50, 20);
        Stock stock5 = new Stock("Special Fried Rice and Chicken Saute", "deluxe meal",8.50, 20);
        Stock stock6 = new Stock("Waakye meal", "deluxe meal",8.50, 20);
        Stock stock7 = new Stock("Chicken nuggets with fries", "deluxe meal",10.50, 20);

        //breakfast
        Stock stock8 = new Stock("bread rolls, sausage, egg", "breakfast meal",6.50, 30);
        Stock stock9 = new Stock("Pancakes, Syrup and Sausages", "breakfast meal",6.50, 30);
        Stock stock10 = new Stock("baked beans, omelette, bread rolls", "breakfast meal",6.50, 30);
        Stock stock11 = new Stock("Fried Rice and Chicken", "breakfast",7.50, 30);

        //drinks
        Stock  stock12 = new Stock("Kalypo", "drink",2.50, 40);
        Stock stock13 = new Stock("Don Simon", "drink",10, 40);
        Stock stock14 = new Stock("Malta Guinness", "drink",4.50, 40);

        ArrayList<Stock> AkornorStocks = new ArrayList<>();
        AkornorStocks.add(stock1);
        AkornorStocks.add(stock2);
        AkornorStocks.add(stock3);
        AkornorStocks.add(stock4);
        AkornorStocks.add(stock5);
        AkornorStocks.add(stock6);
        AkornorStocks.add(stock7);
        AkornorStocks.add(stock8);
        AkornorStocks.add(stock9);
        AkornorStocks.add(stock10);
        AkornorStocks.add(stock11);
        AkornorStocks.add(stock12);
        AkornorStocks.add(stock13);
        AkornorStocks.add(stock14);

        //System.out.println(AkornorStocks);
        AkornorStock AppStock1 = new AkornorStock();
        AppStock1.setStock(AkornorStocks);

        AppStock1.setNumberofStocks(AppStock1.getTotalQuantity(AkornorStocks));

        System.out.println(AppStock1);

    }
}

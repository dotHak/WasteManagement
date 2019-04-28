package com.icp.wastemanagementsystem;

import java.util.ArrayList;

//this class holds all available stocks in Big Ben
public class BigBenStock {
    private ArrayList<Stock> BigBen;
    private int numberofStocks;

    //default constructor
    public BigBenStock(){}

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
    public void removeStocks(int numer){

    }

    @Override
    public String toString() {
        return "BigBenStock{" +
                "BigBen=" + BigBen +
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
        Stock stock1 = new Stock("Fried Rice and Chicken", "full meal",9.50, 30);
        Stock stock2 = new Stock("Jollof Rice and Chicken", "full meal",9.50, 30);
        Stock stock3 = new Stock("Vernicilli Rice and Chicken", "full meal",9.50, 30);
        Stock stock4 = new Stock("Indomie", "full meal",9.50, 30);
        Stock stock5 = new Stock("Red Red and protein", "full meal",9.50, 30);

        //burgers
        Stock stock6 = new Stock("Cheese Burger", "burger",10.50, 10);
        Stock stock7 = new Stock("HamBurger", "deluxe meal",15.00, 10);

        //breakfast
        Stock stock8 = new Stock("bread rolls, sausage, egg", "breakfast meal",7.50, 15);
        Stock stock9 = new Stock("Pancakes, Syrup and Sausages", "breakfast meal",7.50, 15);
        Stock stock10 = new Stock("baked beans, omelette, bread rolls", "breakfast meal",7.50, 15);

        //drinks
        Stock  stock11 = new Stock("Kalypo", "drink",3.50, 30);
        Stock stock12 = new Stock("Don Simon", "drink",12, 30);
        Stock stock13 = new Stock("Malta Guinness", "drink",4.50, 30);
        Stock stock14 = new Stock("Shasta", "drink",4.50, 30);


        ArrayList<Stock> BigBenStocks = new ArrayList<>();
        BigBenStocks.add(stock1);
        BigBenStocks.add(stock2);
        BigBenStocks.add(stock3);
        BigBenStocks.add(stock4);
        BigBenStocks.add(stock5);
        BigBenStocks.add(stock6);
        BigBenStocks.add(stock7);
        BigBenStocks.add(stock8);
        BigBenStocks.add(stock9);
        BigBenStocks.add(stock10);
        BigBenStocks.add(stock11);
        BigBenStocks.add(stock12);
        BigBenStocks.add(stock13);
        BigBenStocks.add(stock14);

        BigBenStock AppStock3 = new BigBenStock();
        AppStock3.setStock(BigBenStocks);
        AppStock3.setNumberofStocks(AppStock3.getTotalQuantity(BigBenStocks));

        System.out.println(AppStock3);

    }
}

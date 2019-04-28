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
    public static void main(String[] args){
        //solid snack
        Stock stock1 = new Stock("plantain chips", "solid snack",1.50, 20);
        Stock stock2 = new Stock("Short bread", "solid snack",8.00, 20);
        Stock stock3 = new Stock("Cadberry cookies", "solid snack",12.00, 20);
        Stock stock4 = new Stock("popcorn", "solid snack",3.50, 20);
        Stock stock5 = new Stock("king cracker", "solid snack",1.50, 20);
        Stock stock6 = new Stock("bounty", "solid snack",6.50, 20);
        Stock stock7 = new Stock("M&Ms", "solid snack",4.50, 20);

        //drinks
        Stock stock8 = new Stock("water", "drink",6.50, 30);
        Stock stock9 = new Stock("yomi yoghurt", "drink",6.50, 30);
        Stock stock10 = new Stock("kalypo", "drink",6.50, 30);
        Stock stock11 = new Stock("Welch's", "drink",7.50, 30);

        //others
        Stock  stock12 = new Stock("Sunlight", "other",5.00, 30);
        Stock stock13 = new Stock("Kaeme Lotion", "other",15.00, 30);
        Stock stock14 = new Stock("Tissue box", "other",10.50, 30);
        Stock stock15 = new Stock("Slippers", "other", 2.00, 30);

        //cooked meal
        Stock stock16 = new Stock("Samosa and Spring rols", "cooked meal",7.00, 15);
        Stock stock17 = new Stock("Sandwich", "cooked meal",9.00, 15);
        Stock stock18 = new Stock("bread pack", "cooked meal",5.00, 15);

        //Stationery
        Stock stock19 = new Stock("Exercise book", "stationery",7.00, 40);
        Stock stock20 = new Stock("ruler", "deluxe meal",2.50, 40);

        ArrayList<Stock> EssentialStocks = new ArrayList<>();
        EssentialStocks.add(stock1);
        EssentialStocks.add(stock2);
        EssentialStocks.add(stock3);
        EssentialStocks.add(stock4);
        EssentialStocks.add(stock5);
        EssentialStocks.add(stock6);
        EssentialStocks.add(stock7);
        EssentialStocks.add(stock8);
        EssentialStocks.add(stock9);
        EssentialStocks.add(stock10);
        EssentialStocks.add(stock11);
        EssentialStocks.add(stock12);
        EssentialStocks.add(stock13);
        EssentialStocks.add(stock14);

        EssentialStock AppStock2 = new EssentialStock();
        AppStock2.setStock(EssentialStocks);


        AppStock2.setNumberofStocks(AppStock2.getTotalQuantity(EssentialStocks));



        System.out.println(AppStock2);

    }

}

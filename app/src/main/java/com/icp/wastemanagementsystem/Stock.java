package com.icp.wastemanagementsystem;

//This class holds all available stocks for the stake holders

public class Stock {
    //instance variables
    private String name;
    private String type;
    private double price;
    private int quantity;

    //default constructor
    public Stock(){
    }
    //overloaded constructor
    public Stock(String S_name, String S_type, double S_price, int S_quantity){
        this.name = S_name;
        this.type =S_type;
        this.price =S_price;
        this.quantity =S_quantity;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }




    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }



    public static void main (String[] args){
        //Stock stock2 = new Stock("a", "b", 45, 56);


    }
}


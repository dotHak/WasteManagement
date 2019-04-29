package com.icp.wastemanagementsystem;

import java.util.ArrayList;

public class getAllStocks {
    public static EssentialStock getEssentialstocks(){
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


        return AppStock2;
    }
    public static AkornorStock getAkornorStocks(){
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

        return AppStock1;
    }
    public static BigBenStock getBigBenStocks(){
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

       return AppStock3;


    }
    public static ZoomlionStock getZoomLionStocks(){
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

        ZoomlionStock AppStock4 = new ZoomlionStock();
        AppStock4.setStock(ZoomlionStocks);


        AppStock4.setNumberofStocks(AppStock4.getTotalQuantity(ZoomlionStocks));


    return AppStock4;
    }

}

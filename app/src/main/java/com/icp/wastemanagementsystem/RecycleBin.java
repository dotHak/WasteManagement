package com.icp.wastemanagementsystem;///// Lisa Princesse Ikirezi

public class RecycleBin <T> {

    // instance variables

    private String location;
    private T []  BinBucket;
    private int BinCapacity;
    private int ElementsInBin=0;

    // Default Constructor
    public RecycleBin(){

        BinBucket= (T[]) new Object [10]; // Standard size of a default bin bucket
    }

    // Overloaded Constructor
    public RecycleBin(String location, int BinCapacity){
        this.location=location;
        this.BinCapacity=BinCapacity;
        BinBucket= (T[]) new Object [BinCapacity];
    }

    // setters
    public void setLocation(String LocationName){
        this.location= LocationName;
    }

    public void setBinCapacity(int BinCapacity){
        this.BinCapacity= BinCapacity;
    }

    public void setBinBucket( T[] bucket){
        this.BinBucket= bucket;
    }

    // getters
    public String getLocation(){
        return location;
    }

    public int getBinCapacity(){
        return BinCapacity;
    }

    public T [] getBinBucket(){
        return BinBucket;
    }

    public int getElementsInBin(){
        return ElementsInBin;
    }

    // Methods

    public void addElementToBin ( T barcode){
        if (ElementsInBin == BinCapacity){
            System.out.println(" The Bin is Full");
        }
        BinBucket[ElementsInBin]=barcode;
        ElementsInBin++;
    }

    public void AddAllElementsToBin( T[] barcodes){

        while (BinCapacity < ElementsInBin+ barcodes.length){
            System.out.println(" The remaining space in bin cannot handle all disposals");
        }

        for ( int i=ElementsInBin; i<ElementsInBin+barcodes.length;i++){

            BinBucket[i]= barcodes[i-ElementsInBin];

        }

        for ( int i=0; i<barcodes.length;i++){
            if ( barcodes[i]!= null){
                ElementsInBin++;
            }
        }
    }

    public  void BinState(){

        if ( ElementsInBin==0){
            System.out.println(" The Bin Bucket is empty");
        }
        else{
            int spaceAvailable= BinCapacity- ElementsInBin;

            System.out.println("The avalaible space in the bin is " + spaceAvailable );
        }
    }

    @Override


    public String toString(){
        return "Bin at "+ this.getLocation() + " has " + this.getElementsInBin()+" elements";
    }

    public void DisplayElementsInBin() {

        System.out.println("The elements in the bin:\n ");
        for ( T element: BinBucket) {

            if ( element== null) {
                continue;
            }

            System.out.println(element);
        }
    }





    public  static void main ( String[] args){

        RecycleBin <Integer> Bin1= new RecycleBin<>(" Library", 20);
        Bin1.addElementToBin(56672021);
        Bin1.addElementToBin(45882021);
        Bin1.addElementToBin(56342021);
        Bin1.addElementToBin(98882021);

        System.out.println(Bin1.toString()+ "\n");

        Bin1.BinState();

        System.out.println();

        Bin1.DisplayElementsInBin();



    }
}

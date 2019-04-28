//
// Created by prili on 27/04/2019.
//

#ifndef WASTEMANAGEMENT_RECYCLEBINS_H
#define WASTEMANAGEMENT_RECYCLEBINS_H

#include <string>
#include <iostream>

using namespace std;

class RecycleBins {

private:
    string location;

    vector<int> BinBucket;

    int BinCapacity;


    RecycleBins();

    RecycleBins(String Location, int capacity);

    ~RecycleBins();

    //getters
    int getBinCapacity();

    vector<int> getBinBucket();

    string getLocation();

    //setters

    void setBinCapacity(int capacity);

    void setBinBucket(vector<int> bucket);

    void setLocation( string location);


    //
    void BinState();





};


#endif //WASTEMANAGEMENT_RECYCLEBINS_H

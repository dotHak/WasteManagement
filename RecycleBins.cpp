//
// Created by prili on 27/04/2019.
//

#include "RecycleBins.h"

#include "vector.h"


RecycleBins::RecycleBins() {
    BinBucket= new vector<>();
}

RecycleBins::RecycleBins(String Location, int capacity) {
    this->location= location;
    this->BinCapacity= capacity;
    BinBucket= new vector<>(capacity);

}

RecycleBins::~RecycleBins() {
    cout<< " Object Deleted"<<endl;
}

// Getters
vector<int> RecycleBins::getBinBucket() {
    return this->BinBucket;
}

int RecycleBins::getBinCapacity() {
    return this->BinCapacity;

}

string RecycleBins::getLocation() {
    return this->location;
}

void RecycleBins::setBinBucket(vector<int> bucket) {
    this->BinBucket= bucket;

}

void RecycleBins::setBinCapacity(int capacity) {
    this->BinCapacity=capacity;
}

void RecycleBins::setLocation(string location) {
    this->location= location;
}

void RecycleBins::BinState() {

    if ( BinBucket.empty()){
        cout<<" The bin is empty"<<endl;
    }

    else {
        cout<< BinBucket.size()<<endl;

        for ( vector Retrieve: BinBucket){
            cout<<Retrieve<<endl;

        }
        cout<< " The bin has "<<BinBucket.capacity()- BinBucket.size() << " space left"<<endl;
    }
}
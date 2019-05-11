package com.icp.wastemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class ArkonoStockAdapter extends ArrayAdapter<Stock> {

    private static final String TAG = "ArkonoStockAdapter";
    private Context mContext;
    int mResource;

    public ArkonoStockAdapter(Context context, int resource, ArrayList<Stock> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        Double price = getItem(position).getPrice();
        int quantity = getItem(position).getQuantity();
        String type = getItem(position).getType();

        Stock stock = new Stock(name, type, price, quantity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView itemName = convertView.findViewById(R.id.listName);
        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        TextView itemQuantity = convertView.findViewById(R.id.itemQuantity);
        TextView itemType = convertView.findViewById(R.id.itemType);
        TextView listNumber = convertView.findViewById(R.id.stockListNumber);

        listNumber.setText(new Integer(position +1).toString());
        itemName.setText(name);
        itemPrice.setText(new Double(price).toString());
        itemType.setText(type);

        if(quantity != 0){
            itemQuantity.setText(new Integer(quantity).toString());
        }else{
            itemQuantity.setText("NA");
        }

        return convertView;
    }
}

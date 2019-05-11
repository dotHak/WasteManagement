package com.icp.wastemanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ArkonoFragment extends Fragment{
    ArrayList<Stock> arkonoList;
    Stock selected;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arkono, container, false);
        arkonoList = getAllStocks.getAkornorStocks().getStock();
        getActivity().setTitle("Arkono Stocks");
        ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.stock_item, arkonoList);
        listView = view.findViewById(R.id.arkonoListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = arkonoList.get(position);
                Intent intent = new Intent(getActivity(), Payment.class);
                intent.putExtra("name", arkonoList.get(position).getName());
                intent.putExtra("quantity", arkonoList.get(position).getQuantity());
                intent.putExtra("price", arkonoList.get(position).getPrice());
                intent.putExtra("position", position);
                intent.putExtra("type", arkonoList.get(position).getType());
                startActivityForResult(intent,1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int quantity = data.getIntExtra("stockQuant",0);
               int position = data.getIntExtra("position", 0);
                arkonoList.get(position).setQuantity(quantity);
                ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.stock_item, arkonoList);
                listView.setAdapter(adapter);
            }

        }
    }
}


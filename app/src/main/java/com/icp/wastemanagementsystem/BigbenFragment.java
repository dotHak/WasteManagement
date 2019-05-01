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
import android.widget.Toast;

import java.util.ArrayList;

public class BigbenFragment extends Fragment {
    ArrayList<Stock> bigBenList;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bigben, container, false);
        bigBenList= getAllStocks.getBigBenStocks().getStock();
        ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.stock_item, bigBenList);
        listView = view.findViewById(R.id.bigBenListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Payment.class);
                intent.putExtra("name", bigBenList.get(position).getName());
                intent.putExtra("quantity", bigBenList.get(position).getQuantity());
                intent.putExtra("price", bigBenList.get(position).getPrice());
                intent.putExtra("position", position);
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
                bigBenList.get(position).setQuantity(quantity);
                ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.stock_item, bigBenList);
                listView.setAdapter(adapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Check the quanity", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


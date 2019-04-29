package com.icp.wastemanagementsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class BigbenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bigben, container, false);
        ArrayList<Stock> bigben = getAllStocks.getBigBenStocks().getStock();
        ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.stock_item, bigben);
        ListView listView = view.findViewById(R.id.bigBenListView);
        listView.setAdapter(adapter);
        return view;
    }
}


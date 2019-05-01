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

public class AwardsFragment extends Fragment {
    ArrayList<User> awards;
    ListView mListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_awards, container, false);

        ArkonoStockAdapter adapter = new ArkonoStockAdapter(getActivity(),R.layout.rank_item, awards);
        mListView = view.findViewById(R.id.arkonoListView);
        mListView.setAdapter(adapter);
    }
}


package com.icp.wastemanagementsystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    ListView mListView;
    SearchView mSearchView;
    ArrayAdapter<String> mStringArrayAdapter;
    String [] data = {"Tito", "Lisa", "Bobby", "Me"};

    DatabaseReference mDatabaseReference;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mListView = view.findViewById(R.id.history_list);
        mStringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(mStringArrayAdapter);
        return view;
    }


}

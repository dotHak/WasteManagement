package com.icp.wastemanagementsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AwardsFragment extends Fragment {
    ListView mListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_awards, container, false);
        getActivity().setTitle("Ranks");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> awards = new ArrayList<>();
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    User user = dsp.getValue(User.class);
                    awards.add(user);
                }

                Collections.sort(awards, Collections.<User>reverseOrder(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.allBarcode() - o2.allBarcode();
                    }
                }));

                if(getActivity() != null) {
                    AwardsAdapter adapter = new AwardsAdapter(getActivity(), R.layout.rank_item, awards);
                    mListView = view.findViewById(R.id.awardsList);
                    mListView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to update list", Toast.LENGTH_SHORT);
            }
        });

        return view;
    }
}


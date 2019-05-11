package com.icp.wastemanagementsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AwardsAdapter extends ArrayAdapter<User> {
    private static final String TAG = "ArkonoStockAdapter";
    private Context mContext;
    private int mResource;

    public AwardsAdapter(Context context, int resource, List<User> objects ){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String userName = getItem(position).getUsername();
        String email = getItem(position).getEmail();
        int credit = getItem(position).getCredit();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView userNameView = convertView.findViewById(R.id.rankUserName);
        TextView emailView = convertView.findViewById(R.id.emailRank);
        TextView creditView = convertView.findViewById(R.id.creditRank);
        TextView rankView = convertView.findViewById(R.id.rankNumber);

        userNameView.setText(userName);
        emailView.setText(email);
        creditView.setText(new Integer(credit).toString());
        rankView.setText(new Integer(position + 1 ).toString());

        return convertView;
    }
}



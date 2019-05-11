package com.icp.wastemanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    Button mMakePayment;
    TextView mQuantityValue;
    TextView mNameValue;
    EditText mQuantityEntered;
    int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mMakePayment = findViewById(R.id.makePayment);
        mQuantityEntered = findViewById(R.id.enteredQuantity);
        mNameValue = findViewById(R.id.itemNameValue);
        mQuantityValue = findViewById(R.id.quantityValue);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        final double price = getIntent().getDoubleExtra("price", 0 );
        final int position = getIntent().getIntExtra("position",0 );
        quantity = getIntent().getIntExtra("quantity",0);
        final String type = getIntent().getStringExtra("type");
        mNameValue.setText(getIntent().getStringExtra("name"));
        mQuantityValue.setText(Integer.toString(quantity));

        if(quantity == 0){
            mQuantityEntered.setEnabled(false);
        }


        mMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent();
                quantity = getIntent().getIntExtra("quantity" ,0);
                final int enteredQuantity = !(type.equals("service")) ? Integer.parseInt(mQuantityEntered.getText().toString() ) : 1;
                if( enteredQuantity  > quantity && !(type.equals("service"))){
                    showErrorDialog("Check the quantity", "Inadequate Quantity");
                }else{
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            if(user.getCredit()*.01 < enteredQuantity * price ){
                                showErrorDialog("Credit not enough", "Insufficient Credit");
                            }else{
                                Toast.makeText(getBaseContext(), "Payment successful", Toast.LENGTH_SHORT).show();
                                user.setCredit((int)(user.getCredit() - enteredQuantity* price*100));
                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                                intent.putExtra("stockQuant", enteredQuantity );
                                intent.putExtra("position", position);
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            showErrorDialog("Error occurred, check internet connection", "Processing Failure");
                        }
                    });
                }
            }
        });


    }

    private void showErrorDialog(String message, String title) {

        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();

    }
}

package com.icp.wastemanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class BarcodeChoiceAcitivty extends AppCompatActivity implements View.OnClickListener {

    // use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;
    private Button mClaimCredit;
    private Button mClose;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String mUserID;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_capture);

        statusMessage = (TextView)findViewById(R.id.status_message);
        barcodeValue = (TextView)findViewById(R.id.barcode_value);

        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);

        mUserID = getSharedPreferences(SignInActivity.PREF_NAME, Context.MODE_PRIVATE)
                .getString(SignInActivity.KEY_USERID, "");


        findViewById(R.id.read_barcode).setOnClickListener(this);
        mClaimCredit = findViewById(R.id.claimCreditButton);
        mClose = findViewById(R.id.closeButton);
        mClaimCredit.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();


        final Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(dashboardIntent);
            }
        });


        mClaimCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       User user = dataSnapshot.child(mUserID).getValue(User.class);
                       boolean isMatched = false;
                       for(DataSnapshot dsp : dataSnapshot.getChildren()){
                           User tempUser = dsp.getValue(User.class);
                           Map <String, List<String>> barcodeList = tempUser.getBarcodeList();
                           if(barcodeList != null){
                               for(Map.Entry<String, List<String>> entry: barcodeList.entrySet()){
                                   ArrayList<String> list = (ArrayList<String>) entry.getValue();
                                   if(list.contains(barcodeValue.getText().toString().trim())){
                                       isMatched = true;
                                       break;
                                   }
                               }
                           }
                       }


                       if(!isMatched) {
                           user.updateList(barcodeValue.getText().toString().trim());

                           mDatabaseReference.child("users").child(mUserID).setValue(user)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void aVoid) {
                                           Toast.makeText(getBaseContext(), "You have earned 20 pesewas", Toast.LENGTH_SHORT).show();
                                           finish();
                                           startActivity(dashboardIntent);
                                       }
                                   })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           showErrorDialog("Update failed", "Update Failure");
                                       }
                                   });
                       }else{
                           showErrorDialog("Barcode already exists", "Update Failure");
                       }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        showErrorDialog("Services not available right now \n Check your internet connection", "Update Failure");
                    }
                });


            }
        });
    }


    private void showErrorDialog(String message, String title) {

        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();

    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_barcode) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    barcodeValue.setText(barcode.displayValue);
                    mClaimCredit.setEnabled(true);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


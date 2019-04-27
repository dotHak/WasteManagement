package com.icp.wastemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(SignInActivity.PREF_NAME, Context.MODE_PRIVATE);
        if(prefs.getBoolean(SignInActivity.KEY_AUTOLOG, false)){
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(prefs.getBoolean(SignInActivity.KEY_LOGGED,false)){
            Intent intent = new Intent(this, SignInActivity.class);
            intent.putExtra("backOnExist", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
    }


    public void openSignUpForm(View view) {
        Intent stakeholdersIntent = new Intent(this, StakeholdersActivity.class);
        stakeholdersIntent.putExtra("buttonType", "signUp");
        startActivity(stakeholdersIntent);
    }

    public void openSignInForm(View view) {
        Intent signInIntent = new Intent(this, StakeholdersActivity.class);
        signInIntent.putExtra("buttonType", "signIn");
        startActivity(signInIntent);
    }

    @Override
    public void onBackPressed(){
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
        }else{
            Toast.makeText(getBaseContext(), "Press BACK again to exit", Toast.LENGTH_SHORT).show();}
        mBackPressed = System.currentTimeMillis();
    }
}

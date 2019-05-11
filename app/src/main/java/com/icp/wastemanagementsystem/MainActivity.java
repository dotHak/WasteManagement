package com.icp.wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

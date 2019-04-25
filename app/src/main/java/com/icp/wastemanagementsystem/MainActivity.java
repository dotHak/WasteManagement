package com.icp.wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openSignUpForm(View view) {
        Intent stakeholdersIntent = new Intent(this, Stakeholders.class);
        stakeholdersIntent.putExtra("buttonType", "signUp");
        startActivity(stakeholdersIntent);
    }

    public void openSignInForm(View view) {
        Intent signInIntent = new Intent(this, Stakeholders.class);
        signInIntent.putExtra("buttonType", "signIn");
        startActivity(signInIntent);
    }
}

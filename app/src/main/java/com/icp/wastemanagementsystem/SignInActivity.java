package com.icp.wastemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private CheckBox mRememberMe;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mSharedEditor;


    static final String PREF_NAME = "WasteLogPrefs";
    static final String KEY_REMEMBER= "rememberMe";
    static final String  KEY_EMAIL = "email";
    static  final String KEY_PASSWORD = "password";
    static final String KEY_LOGGED = "hasBeenLogged";
    static final String KEY_AUTOLOG = "autoLogIn";

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private Boolean mEnableDoubleBackExist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        mSharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mSharedEditor = mSharedPreferences.edit();
        mEmailView = findViewById(R.id.emailField);
        mPasswordView = findViewById(R.id.passwordField);
        mLoginButton = findViewById(R.id.logInButton);
        mRememberMe = findViewById(R.id.rememberMe);
        mAuth = FirebaseAuth.getInstance();

        mEnableDoubleBackExist = getIntent().getBooleanExtra("backOnExist", false);

        if(mSharedPreferences.getBoolean(KEY_REMEMBER, false)){
            mRememberMe.setChecked(true);
        }else{
            mRememberMe.setChecked(false);
        }

        mEmailView.setText(mSharedPreferences.getString(KEY_EMAIL, ""));
        mPasswordView.setText(mSharedPreferences.getString(KEY_PASSWORD,""));

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

    }


    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.isEmpty())
            if (email.equals("") || password.equals("")) return;

        mLoginButton.setEnabled(false);
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    showErrorDialog("There was a problem signing in");
                    mLoginButton.setEnabled(true);
                } else {
                    manageSharedPrefs();
                    Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });


    }


    private void showErrorDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void openSignUpForm(View view){
        Intent stakeholdersIntent = new Intent(this, SignUpActivity.class);
        stakeholdersIntent.putExtra("buttonType", "signUp");
        startActivity(stakeholdersIntent);
    }

    private void manageSharedPrefs(){
        if (mRememberMe.isChecked()){
            mSharedEditor.putString(KEY_EMAIL, mEmailView.getText().toString().trim());
            mSharedEditor.putString(KEY_PASSWORD, mPasswordView.getText().toString());
            mSharedEditor.putBoolean(KEY_REMEMBER, true);
            mSharedEditor.putBoolean(KEY_AUTOLOG,true);
            if(!(mSharedPreferences.getBoolean(KEY_LOGGED,false))){
                mSharedEditor.putBoolean(KEY_LOGGED,true);
            }
            mSharedEditor.apply();

        }else{
            mSharedEditor.putBoolean(KEY_REMEMBER, false);
            mSharedEditor.remove(KEY_PASSWORD);
            mSharedEditor.remove(KEY_EMAIL);
            mSharedEditor.apply();
        }
    }

    @Override
    public void onBackPressed(){
        if(mEnableDoubleBackExist) {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Press BACK again to exit", Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }else{
            super.onBackPressed();
        }
    }

}




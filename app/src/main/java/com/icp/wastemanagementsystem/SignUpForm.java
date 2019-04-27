package com.icp.wastemanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpForm extends AppCompatActivity {

    private EditText mEmailView;
    private TextInputEditText mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private Button mSignUpButton;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mUsernameView = findViewById(R.id.signUpUserNameField);
        mEmailView = findViewById(R.id.signUpEmailField);
        mPasswordView = findViewById(R.id.signUpPasswordField);
        mConfirmPasswordView = findViewById(R.id.signupConfirmPasswordField);
        mSignUpButton = findViewById(R.id.sigupRegisterButton);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               attemptRegistration();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }


    private void attemptRegistration() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(confirmPassword)){
            mConfirmPasswordView.setError(getString(R.string.error_field_required));
            focusView = mConfirmPasswordView;
            cancel = true;
        }if(!confirmPassword.equals(password)){
            mConfirmPasswordView.setError(getString(R.string.error_not_match_password));
            focusView = mConfirmPasswordView;
            cancel = true;
        }


        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }




        if (cancel) {
            focusView.requestFocus();
        } else {
            createFirebaseUser();

        }
    }


    private boolean isPasswordValid (String password){
        return password.length() > 4;
    }

    private boolean isEmailValid (String email){
        return email.contains("@");
    }

    private void createFirebaseUser() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
            new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        showErrorDialog("Registration attempt failed");
                    } else {
                        saveLogIn();
                        Intent intent = new Intent(SignUpForm.this, Navigation.class);
                        finish();
                        startActivity(intent);
                    }
                }
            });
    }


    private void showErrorDialog ( String message )  {

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    public void openSignInForm(View view){
        Intent signInIntent = new Intent(this, SignInForm.class);
        signInIntent.putExtra("buttonType", "signIn");
        startActivity(signInIntent);
    }

    private void saveLogIn(){
        SharedPreferences prefs = getSharedPreferences(SignInForm.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = prefs.edit();
        sharedEditor.putString(SignInForm.KEY_EMAIL, mEmailView.getText().toString().trim());
        sharedEditor.putString(SignInForm.KEY_PASSWORD, mPasswordView.getText().toString());
        sharedEditor.putBoolean(SignInForm.KEY_REMEMBER, true);
        sharedEditor.putBoolean(SignInForm.KEY_AUTOLOG,true);
        if(!(prefs.getBoolean(SignInForm.KEY_LOGGED,false))){
            sharedEditor.putBoolean(SignInForm.KEY_LOGGED,true);
        }
        sharedEditor.apply();
    }


}

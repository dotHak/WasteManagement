package com.icp.wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInForm extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mEmailView = findViewById(R.id.emailField);
        mPasswordView = findViewById(R.id.passwordField);
        mLoginButton = findViewById(R.id.logInButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }


    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.isEmpty())
            if (email.equals("") || password.equals("")) return;
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    showErrorDialog("There was a problem signing in");
                } else {
                    Intent intent = new Intent(SignInForm.this, Navigation.class);
                    finish();
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

}


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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailView;
    private TextInputEditText mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private Button mSignUpButton;

    static FirebaseAuth mAuth;
    static DatabaseReference mDatabaseReference;


    private User mUser;
    static String mUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mUsernameView = findViewById(R.id.signUpUserNameField);
        mEmailView = findViewById(R.id.signUpEmailField);
        mPasswordView = findViewById(R.id.signUpPasswordField);
        mConfirmPasswordView = findViewById(R.id.signupConfirmPasswordField);
        mSignUpButton = findViewById(R.id.sigupRegisterButton);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                attemptRegistration();
            }
            });
    }

    private void attemptRegistration() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        final String userName = mUsernameView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(confirmPassword)) {
            mConfirmPasswordView.setError(getString(R.string.error_field_required));
            focusView = mConfirmPasswordView;
            cancel = true;
        }
        if (!confirmPassword.equals(password)) {
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

        if(TextUtils.isEmpty(userName)){
            mUsernameView.setError("This field is required");
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean isMatched = false;
                    for(DataSnapshot dsp: dataSnapshot.getChildren()){
                        User tempUser = dsp.getValue(User.class);
                        if(tempUser.getUsername().equals(userName)){
                            isMatched =true;
                            break;
                        }
                    }

                    if(!(isMatched)){
                        createFirebaseUser();
                    }else{
                        mUsernameView.setError("Username already exists");
                        mUsernameView.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    showErrorDialog("Resgiration attemp failed, check internet connection!", "Error");
                }
            });

        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private void createFirebaseUser() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String userName = mUsernameView.getText().toString().trim();

        mUser = new User(userName, email, 0);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            showErrorDialog("Registration attempt failed","Oops");
                        } else {
                            saveLogIn();
                            mDatabaseReference.child("users").child(mAuth.getCurrentUser().getUid()).setValue(mUser);
                            Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
    }



    private void showErrorDialog(String message, String title) {

        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();

    }

    public void openSignInForm(View view) {
        Intent signInIntent = new Intent(this, SignInActivity.class);
        signInIntent.putExtra("buttonType", "signIn");
        startActivity(signInIntent);
    }

    private void saveLogIn() {
        SharedPreferences prefs = getSharedPreferences(SignInActivity.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedEditor = prefs.edit();
        mUserId = mAuth.getCurrentUser().getUid();
        sharedEditor.putString(SignInActivity.KEY_EMAIL, mEmailView.getText().toString().trim());
        sharedEditor.putString(SignInActivity.KEY_PASSWORD, mPasswordView.getText().toString());
        sharedEditor.putString(SignInActivity.KEY_USERNAME, mUsernameView.getText().toString().trim());
        sharedEditor.putString(SignInActivity.KEY_USERID, mUserId);
        sharedEditor.putBoolean(SignInActivity.KEY_AUTOLOG, true);
        if (!(prefs.getBoolean(SignInActivity.KEY_LOGGED, false))) {
            sharedEditor.putBoolean(SignInActivity.KEY_LOGGED, true);
        }
        sharedEditor.apply();
    }
}

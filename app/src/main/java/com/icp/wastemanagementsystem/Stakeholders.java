package com.icp.wastemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Stakeholders extends AppCompatActivity {

    private  Button mStudentButton;
    private Button mEssentialButton;
    private Button mArkonoButton;
    private Button mBigBenButton;
    private Button mZoomlionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stakeholders);

        mStudentButton = findViewById(R.id.studentsButton);
        mEssentialButton = findViewById(R.id.EssentialsButton);
        mArkonoButton = findViewById(R.id.arkonoButton);
        mBigBenButton = findViewById(R.id.bigBenButton);
        mZoomlionButton = findViewById(R.id.zoomlionButton);

        Intent openForm = getIntent();
        String buttonType= openForm.getExtras().getString("buttonType");

        if(buttonType.equals("signUp")){
            mEssentialButton.setEnabled(false);
            mBigBenButton.setEnabled(false);
            mZoomlionButton.setEnabled(false);
            mArkonoButton.setEnabled(false);

            final Intent signUpFormIntent = new Intent(this, SignUpForm.class);
            mStudentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(signUpFormIntent);
                }
            });
        }else if(buttonType.equals("signIn")){
            Button [] allButtons = {mStudentButton, mEssentialButton, mBigBenButton,
                                mArkonoButton, mZoomlionButton};
            final Intent signInButtonIntent = new Intent(this, SignInForm.class);
            for(Button button: allButtons){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(signInButtonIntent);
                    }
                });
            }
        }
    }
}

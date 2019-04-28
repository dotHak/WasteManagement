package com.icp.wastemanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private TextView mUsernameView;
    private TextView mEmailView;
    private TextView mCreditView;
    private SharedPreferences mSharedPreferences;
    static FirebaseAuth mAuth;
    static DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        mSharedPreferences = getSharedPreferences(SignInActivity.PREF_NAME, Context.MODE_PRIVATE);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        mEmailView=  headerView.findViewById(R.id.navEmailView);
        mUsernameView = headerView.findViewById(R.id.navUsernameView);
        mCreditView = headerView.findViewById(R.id.creditText);

        mEmailView.setText(mSharedPreferences.getString(SignInActivity.KEY_EMAIL, "Email"));


        final Intent barcodeScanIntent = new Intent(this, BarcodeChoiceAcitivty.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(barcodeScanIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mDatabaseReference.child(mSharedPreferences.getString(SignInActivity.KEY_USERID, "")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
               mUsernameView.setText(user.getUsername());
               mCreditView.setText(Integer.toString(user.getCredit()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
                super.onBackPressed();
            }else{
                Toast.makeText(getBaseContext(), "Press BACK again to exit", Toast.LENGTH_SHORT).show();}
            mBackPressed = System.currentTimeMillis();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(this, SignInActivity.class);
            mSharedPreferences.edit().putBoolean(SignInActivity.KEY_AUTOLOG,false).apply();
            intent.putExtra("backOnExist", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle dashboard view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            // Handle the camera action
        } else if (id == R.id.nav_scan_barcode) {
            startActivity(new Intent(this, BarcodeChoiceAcitivty.class));
        } else if (id == R.id.nav_bins) {

        } else if (id == R.id.nav_awards) {

        } else if (id == R.id.nav_essentials) {

        } else if (id == R.id.nav_arkono) {

        }else if (id == R.id.nav_zoomlion){

        }else if (id == R.id.nav_bigBen){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

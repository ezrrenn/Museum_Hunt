package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project1.databinding.ActivityMainBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends Menu {

    ActivityMainBinding activityMainBinding;
    TextView tvFirstNameMain, tvPhoneNumber, tvEmailMain;
    ImageButton openActivity;
    Button btnEdit;

    FirebaseFirestore fStore;
    FirebaseAuth mauth;
    String userID;
    FirebaseUser currentUser;
    //DatabaseReference reference;
   // FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("My Profile");

        //Initialize Firebase Auth
        fStore = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        userID = mauth.getCurrentUser().getUid();

        tvFirstNameMain = findViewById(R.id.tvFirstNameMain);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvEmailMain = findViewById(R.id.tvEmailMain);
        openActivity = (ImageButton) findViewById(R.id.imageButton);
        openActivity = (ImageButton) findViewById(R.id.imageButton2);

        ImageButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,UpdateActivity.class);
                i.putExtra("firstName", tvFirstNameMain.getText().toString().trim());
                i.putExtra("phoneNumber", tvPhoneNumber.getText().toString().trim());
                i.putExtra("email", tvEmailMain.getText().toString().trim());
                startActivity(i);
            }
        });

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tvFirstNameMain.setText(value.getString("firstName"));
                tvPhoneNumber.setText(value.getString("phoneNumber"));
                tvEmailMain.setText(value.getString("email"));

            }
        });
    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void openActivity3 (){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void openActivity2 (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
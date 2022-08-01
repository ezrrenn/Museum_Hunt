package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project1.databinding.ActivityHistoryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class histActivity extends Menu{

    ActivityHistoryBinding activityHistBinding;
    TextView tvN, tvE, tvM, tvT, tvP, tvD;
    Button btnDone;

    FirebaseAuth auth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser currentUser;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistBinding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(activityHistBinding.getRoot());
        allocateActivityTitle("History Booking");

        tvN = findViewById(R.id.tvN);
        tvE = findViewById(R.id.tvE);
        tvM = findViewById(R.id.tvM);
        tvD = findViewById(R.id.tvD);
        tvT = findViewById(R.id.tvT);
        tvP = findViewById(R.id.tvP);
        btnDone = findViewById(R.id.btnDone);

        //Initialize Firebase Auth
        fStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            return;
        }

        documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tvN.setText(value.getString("firstName"));
                tvE.setText(value.getString("email"));

            }
        });

        Intent receiveIntent = getIntent();
        String selectedOption1 = receiveIntent.getStringExtra("Museum: ");
        String dateView = receiveIntent.getStringExtra("Date: ");
        String radioButton = receiveIntent.getStringExtra("Time: ");
        String etTotPerson = receiveIntent.getStringExtra("Number of Person: ");
        tvM.setText(selectedOption1);
        tvD.setText(dateView);
        tvT.setText(radioButton);
        tvP.setText(etTotPerson);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoneBtn();
            }
        });

    }

    public void DoneBtn(){

        Map<String,Object> cust = new HashMap<>();
        cust.put("Name", tvN.getText().toString().trim());
        cust.put("Email", tvE.getText().toString().trim());
        cust.put("Museum", tvM.getText().toString().trim());
        cust.put("Date", tvD.getText().toString().trim());
        cust.put("Time", tvT.getText().toString().trim());
        cust.put("Person", tvP.getText().toString().trim());
        fStore.collection("customerDetails").add(cust).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    showDashActivity();
                }else {
                    Toast.makeText(histActivity.this, "Store failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showDashActivity(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}

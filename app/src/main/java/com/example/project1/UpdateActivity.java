package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project1.databinding.ActivityHistoryBinding;
import com.example.project1.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends Menu {

    ActivityUpdateBinding activityUpdateBinding;

    EditText etUpdateName;
    EditText etUpdatePhone;
    EditText etUpdateEmail;
    Button btnUpdate;
    //FirebaseStorage firebaseStorage;
    //StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateBinding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(activityUpdateBinding.getRoot());
        allocateActivityTitle("Update Profile");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdateEmail = findViewById(R.id.etUpdateEmail);
        etUpdatePhone = findViewById(R.id.etUpdatePhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent data = getIntent();
        String tvFirstNameMain = data.getStringExtra("firstName");
        String tvEmailMain = data.getStringExtra("email");
        String tvPhoneNumber = data.getStringExtra("phoneNumber");

        etUpdateName.setText(tvFirstNameMain);
        etUpdateEmail.setText(tvEmailMain);
        etUpdatePhone.setText(tvPhoneNumber);

        Log.d("TAG", "onCreate: " + tvFirstNameMain + " " + tvEmailMain + " " + tvPhoneNumber);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUpdateName.getText().toString().isEmpty() || etUpdateEmail.getText().toString().isEmpty() || etUpdatePhone.getText().toString().isEmpty()){
                    Toast.makeText(UpdateActivity.this, "One or Many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                String email = etUpdateEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email", email);
                        edited.put("firstName", etUpdateName.getText().toString());
                        edited.put("phoneNumber", etUpdatePhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UpdateActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        });
                        Toast.makeText(UpdateActivity.this, "Changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}
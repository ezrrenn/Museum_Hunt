package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DatabaseReference reference;
    String userID;

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    String[] userType = {"Admin", "Member"};
    String selectedOption;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        //reference = database.getInstance

        textInputLayout = findViewById(R.id.menu_drop);
        autoCompleteTextView = findViewById(R.id.drop_items);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(RegisterActivity.this , R.layout.items_list, userType);
        autoCompleteTextView.setAdapter(itemAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedOption =adapterView.getItemAtPosition(position).toString();

                // if (adapterView.getItemAtPosition(position).equals("Admin")){
                //  showAdmin();
                // }else if (adapterView.getItemAtPosition(position).equals("Member")){
                // showMainActivity();
                //  }
            }
        });


        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.equalsIgnoreCase("Admin")){
                    registerUser2();
                }else {
                    registerUser1();
                }
            }
        });

        TextView textViewSwitchToLogin = findViewById(R.id.tvSwitchToLogin);
        textViewSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });

    }

    private void registerUser1(){
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        EditText etRegisterEmail = findViewById(R.id.etRegisterEmail);
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);


        String firstName = etFirstName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();



        if (firstName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("firstName", firstName);
                            user.put("phoneNumber", phoneNumber);
                            user.put("email", email);
                            user.put("userType", selectedOption);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profile is created for " + userID);
                                }
                            });

                            // User user = new User(firstName, phoneNumber, email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showMainActivity();
                                        }
                                    });

                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void registerUser2(){
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        EditText etRegisterEmail = findViewById(R.id.etRegisterEmail);
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);


        String firstName = etFirstName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();



        if (firstName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("firstName", firstName);
                            user.put("phoneNumber", phoneNumber);
                            user.put("email", email);
                            user.put("userType", selectedOption);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profile is created for " + userID);
                                }
                            });

                            // User user = new User(firstName, phoneNumber, email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            showAdmin();
                                        }
                                    });

                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void showAdmin(){
        //Intent intent = new Intent(this,adActivity.class);
        //startActivity(intent);
       // finish();
    }

    private void showMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void switchToLogin(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
}

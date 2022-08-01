package com.example.project1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project1.databinding.ActivityBookingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.DataInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class bookActivity extends Menu{
    ActivityBookingBinding activityBookingBinding;


    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    String value;
    EditText etTotPerson;

    Calendar calendar;
    TextView dateView;
    int year, month, day;

    FirebaseFirestore fStore;
    FirebaseAuth auth;

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    String selectedOption1;
    String[] museum = {"Baba & Nyonya Heritage", "Stadthuys", "Malay Living", "Cheng Ho Cultural", "Flora de la Mar Maritime Museum", "Malaysia Prison Museum"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookingBinding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(activityBookingBinding.getRoot());
        allocateActivityTitle("Booking");

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Spinner
        textInputLayout = findViewById(R.id.menu_drop1);
        autoCompleteTextView = findViewById(R.id.drop_items1);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(bookActivity.this , R.layout.items_list, museum);
        autoCompleteTextView.setAdapter(itemAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedOption1 =adapterView.getItemAtPosition(position).toString();

            }
        });

        //Date
        dateView = (TextView) findViewById(R.id.tvDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        //Time
        radioGroup = findViewById(R.id.radioGroup1);
        textView = findViewById(R.id.text_view_selected);
        etTotPerson = (EditText) findViewById(R.id.etTotPerson);

        //button

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
               // textView.setText("Choice: " + radioButton.getText());
                Intent senderIntent = new Intent(bookActivity.this, histActivity.class);
                senderIntent.putExtra("Museum: ", selectedOption1);
                senderIntent.putExtra("Date: ", dateView.getText().toString().trim());
                senderIntent.putExtra("Time: ", radioButton.getText().toString().trim());
                senderIntent.putExtra("Number of Person: ", etTotPerson.getText().toString().trim());
                startActivity(senderIntent);
                submitForm();

            }
        });
    }

    //Date
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        //Toast.makeText(getApplicationContext(), "ca",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    //end date

    //time
    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected:" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void submitForm(){
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        textView = (TextView) findViewById(R.id.text_view_selected);
        etTotPerson = (EditText) findViewById(R.id.etTotPerson);


        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);


        Map<String,Object> bookItems = new HashMap<>();
        bookItems.put("Museum", selectedOption1);
        bookItems.put("Date", dateView.getText().toString().trim());
        bookItems.put("Time", radioButton.getText().toString().trim());
        bookItems.put("Person", etTotPerson.getText().toString().trim());
        fStore.collection("bookingDetail").add(bookItems).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(bookActivity.this, "Store failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}

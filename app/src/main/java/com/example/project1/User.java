package com.example.project1;

import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class User {
    public String firstName;
    public String phoneNumber;
    public String email;
    public AutoCompleteTextView autoCompleteTextView;

    public User(){

    }

    public User(String firstName, String phoneNumber, String email){
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public AutoCompleteTextView getAutoCompleteTextView(){
        return autoCompleteTextView;
    }

    public void setAutoCompleteTextView (AutoCompleteTextView autoCompleteTextView) {
        this.autoCompleteTextView = autoCompleteTextView;
    }

    public User(AutoCompleteTextView autoCompleteTextView) {
        this.autoCompleteTextView = autoCompleteTextView;
    }
}

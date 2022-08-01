package com.example.project1;

import android.widget.RadioButton;

public class Booking {

    public String spinner_museum;
    public String dateView;
    public RadioButton radioButton;
    public String totPerson;

    public Booking(String spinner_museum, String dateView, RadioButton radioButton, String totPerson) {
        this.spinner_museum = spinner_museum;
        this.dateView = dateView;
        this.radioButton = radioButton;
        this.totPerson = totPerson;
    }

    public String getSpinner_museum() {
        return spinner_museum;
    }

    public void setSpinner_museum(String spinner_museum) {
        this.spinner_museum = spinner_museum;
    }

    public String getDateView() {
        return dateView;
    }

    public void setDateView(String dateView) {
        this.dateView = dateView;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public String getTotPerson() {
        return totPerson;
    }

    public void setTotPerson(String totPerson) {
        this.totPerson = totPerson;
    }
}

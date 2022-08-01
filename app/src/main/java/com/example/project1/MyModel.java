package com.example.project1;

public class MyModel {

    String id, name, email, museum, date, time, person;

    public MyModel(){}


    public MyModel(String id, String name, String email, String museum, String date, String time, String person){
        this.id=id;
        this.name = name;
        this.email = email;
        this.museum=museum;
        this.date = date;
        this.time=time;
        this.person=person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMuseum() {
        return museum;
    }

    public void setMuseum(String museum) {
        this.museum = museum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

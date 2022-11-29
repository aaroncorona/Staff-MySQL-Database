package com.example.aaroncorona_cs56_proj9.model;

// Represents the data model for a Staff table
public class Staff {

    private final int id;
    private String firstName;
    private String lastName;
    private char mi;
    private String address;
    private String city;
    private String state;
    private int phone;
    private String email;

    public Staff(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Staff(int id, String firstName, String lastName,
                 char mi, String address,String city, String state, int phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.mi = mi;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getMi() {
        return mi;
    }

    public void setMi(char mi) {
        this.mi = mi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


package com.example.holytreeapp;

public class UserModel {
String fname;
String phone;
String address;
String mail;

public UserModel(){}
    public UserModel(String fname, String phone, String address, String mail) {
        this.fname = fname;
        this.phone = phone;
        this.address = address;
        this.mail = mail;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

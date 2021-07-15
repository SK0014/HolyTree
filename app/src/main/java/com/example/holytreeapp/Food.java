package com.example.holytreeapp;

public class Food {
    String details,price,name,qnty;

    public Food(){ }
    public Food(String name, String details, String price,String qnty) {
        this.details = details;
        this.price = price;
        this.name=name;
        this.qnty=qnty;
    }

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.example.holytreeapp;

public class FoodModel {
    String details,price;

    public FoodModel(String details, String price) {
        this.details = details;
        this.price = price;
    }

    public FoodModel() {
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

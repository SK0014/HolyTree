package com.example.holytreeapp;

public class membership_types {
    String details;
    String price;
    String heading;
    int image;

    public membership_types(String details, String heading, int image, String price) {
        this.details = details;
        this.heading = heading;
        this.image = image;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public membership_types() {
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHeading() { return heading; }

    public void setHeading(String heading) { this.heading = heading; }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}

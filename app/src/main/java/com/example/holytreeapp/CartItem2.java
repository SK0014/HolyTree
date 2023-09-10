package com.example.holytreeapp;

public class CartItem2 {
    String item_name,item_price,item_qnty;

    public CartItem2() {
    }

    public CartItem2(String item_name, String item_price, String item_qnty) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_qnty = item_qnty;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_qnty() {
        return item_qnty;
    }

    public void setItem_qnty(String item_qnty) {
        this.item_qnty = item_qnty;
    }
}

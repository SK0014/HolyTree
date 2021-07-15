package com.example.holytreeapp;

public class CartItem {
    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

     int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //// cart item
     String productTitle;
     String productPrice;
     String productQuantity;

    public CartItem(int type, String productTitle, String productPrice, String productQuantity) {
        this.type = type;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }


    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    //// cart item

    //// cart total
     String totalItems;
     String totalItemPrice;
     String deliveryPrice;
     String totalAmount;

    public CartItem(int type, String totalItems, String totalItemPrice, String deliveryPrice, String totalAmount) {
        this.totalAmount = totalAmount;
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemPrice = totalItemPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryAmount(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    //// cart total

}

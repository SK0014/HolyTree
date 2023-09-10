package com.example.holytreeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddCart extends AppCompatActivity {
RecyclerView recyclerView;
CartAdapter2 cartAdapter;
ArrayList<CartItem2> arr;
TextView total_prc;
Button btn_pay;
FirebaseFirestore fb;
FirebaseAuth mauth;
TextView itemsPrice,totalPay;
DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       db=new DbHelper(this);
        setContentView(R.layout.activity_add_cart);
        mauth=FirebaseAuth.getInstance();
        String a;
        if(mauth.getCurrentUser().getEmail()==null){
            a=mauth.getCurrentUser().getPhoneNumber().substring(3);
        }
        else{
            a=mauth.getCurrentUser().getEmail();
        }
        fb=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.cart_items);
        btn_pay=findViewById(R.id.pay);
        getSupportActionBar().hide();
        arr=new ArrayList<>();
        db=new DbHelper(this);
        arr=db.getAlldata();
        //arr.add(new CartItem(1, "price ("+arr.size()+" Dishes)", "Rs "+String.valueOf(db.getSumValue()), "Free", "Rs "+String.valueOf(db.getSumValue())));
        cartAdapter=new CartAdapter2(getApplicationContext(),arr);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyItemChanged(1);
        cartAdapter.notifyItemChanged(2);
        cartAdapter.notifyItemChanged(4);
        cartAdapter.notifyDataSetChanged();
        totalPay=findViewById(R.id.total_pay);
        itemsPrice=findViewById(R.id.total_items_price);
        totalPay.setText("Rs "+String.valueOf(db.getSumValue()));
        itemsPrice.setText("Rs "+String.valueOf(db.getSumValue()));
    }
}
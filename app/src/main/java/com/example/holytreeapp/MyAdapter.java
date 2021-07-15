package com.example.holytreeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    ArrayList<Food> foods;
MyAdapter(Context context,ArrayList<Food>foods){
    this.context=context;
    this.foods=foods;
}
    @NonNull
    @NotNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.menu_layout, parent, false);
        MyHolder myHolder = new MyHolder(listItem);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyHolder holder, int position) {
    Food food=foods.get(position);
    holder.txtdetails.setText(food.getDetails());
        holder.txtprice.setText(food.getPrice());
        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return foods.size() ;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       public ImageView imageView;
       public TextView txtdetails;
       public TextView txtprice;
       public Button addbtn;
        public MyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img_food);
            this.txtdetails = (TextView) itemView.findViewById(R.id.food_dtl);
            this.txtprice = (TextView) itemView.findViewById(R.id.txt_prc);
            this.addbtn = (Button) itemView.findViewById(R.id.cart_btn);
        }
    }
}

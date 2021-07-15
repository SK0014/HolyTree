package com.example.holytreeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<CartItem>cartItems;
    DbHelper dbHelper;
    DbMenu dbMenu;

    public CartAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }
    @Override
    public int getItemViewType ( int position){
        switch (cartItems.get(position).getType()) {
            case 0:
                return CartItem.CART_ITEM;
            case 1:
                return CartItem.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        switch(viewType){
            case CartItem.CART_ITEM:LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View listItem= layoutInflater.inflate(R.layout.cart_item, parent, false);
                CartAdapter.CartHolder cartHolder = new CartAdapter.CartHolder(listItem);
                return cartHolder;
            case CartItem.TOTAL_AMOUNT:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View totalItem= inflater.inflate(R.layout.card_total_amount_layout, parent, false);
                CartAdapter.TotalHolder totalHolder = new CartAdapter.TotalHolder(totalItem);
                return totalHolder;
            default: return null;
        }

    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItems.get(position).getType()) {
            case CartItem.CART_ITEM:
                String title = cartItems.get(position).getProductTitle();
                String productPrice = cartItems.get(position).getProductPrice();
                String qnty = cartItems.get(position).getProductQuantity();

                ((CartHolder) holder).setItemDetails(title, productPrice, qnty,position);
                break;
            case CartItem.TOTAL_AMOUNT:
                String totalItems = cartItems.get(position).getTotalItems();
                String totalItemPrice = cartItems.get(position).getTotalItemPrice();
                String deliveryPrice = cartItems.get(position).getDeliveryPrice();
                String totalAmount = cartItems.get(position).getTotalAmount();
                ((TotalHolder) holder).setTotalAmount(totalItems, totalItemPrice, deliveryPrice, totalAmount);
                break;
            default:
                return;
        }

    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        TextView txttotal;
        TextView txtqnty;
        TextView inr;
        FloatingActionButton add;
        FloatingActionButton reduce;
        public CartHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.cart_name);
            txttotal=itemView.findViewById(R.id.cart_prc);
            txtqnty=itemView.findViewById(R.id.cart_qnt);
            add=itemView.findViewById(R.id.total_add);
            reduce=itemView.findViewById(R.id.total_remove);
            inr=itemView.findViewById(R.id.cart_inr);
        }

        public void setItemDetails(String title, String productPrice, String qnty, int position) {
            dbHelper=new DbHelper(context);
            dbMenu=new DbMenu(context);
            txtname.setText(title);
            txtqnty.setText(qnty);

            txttotal.setText(String.valueOf(Integer.parseInt(qnty)*Integer.parseInt(productPrice)));
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String j=txtqnty.getText().toString();
                    int i;
                    i=Integer.parseInt(j);
                    i++;
                    txtqnty.setText(String.valueOf(i));
                    dbHelper.updateQuantity(title,String.valueOf(i));
                    dbMenu.updateQuantity(title,String.valueOf(i));
                    txttotal.setText(String.valueOf(Integer.parseInt(txttotal.getText().toString())+Integer.parseInt(productPrice)));
                    Toast.makeText(context, "Added to cart "+i, Toast.LENGTH_SHORT).show();

                }
            });
            reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String j=txtqnty.getText().toString();
                    int i=Integer.parseInt(j);
                    i--;
                    if(i<1){
                        cartItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,cartItems.size());
                        dbHelper.deleteItem(title);
                        dbMenu.deleteItem(title);
                    }
                    else{
                        dbHelper.updateQuantity(title,String.valueOf(i));
                        dbMenu.updateQuantity(title,String.valueOf(i));
                        txtqnty.setText(String.valueOf(i));
                        txttotal.setText(String.valueOf(Integer.parseInt(txttotal.getText().toString())-Integer.parseInt(productPrice)));
                    }
                    Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class TotalHolder extends RecyclerView.ViewHolder {

        TextView totalItems;
        TextView totalItemPrice;
        TextView deliveryPrice;
        TextView totalAmount;

        public TotalHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
        }
        public void setTotalAmount(String totalItemText, String totalItemPriceText, String deliveryPriceText, String totalAmountText){
            dbHelper=new DbHelper(context);
            totalItems.setText(totalItemText);
            totalItemPrice.setText(String.valueOf(dbHelper.getSumValue()));
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(String.valueOf(dbHelper.getSumValue()));
        }
    }
}

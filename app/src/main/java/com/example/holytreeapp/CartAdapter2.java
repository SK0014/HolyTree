package com.example.holytreeapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter2 extends RecyclerView.Adapter<CartAdapter2.CartHolder> {
    Context context;
    ArrayList<CartItem2>cartItems;
    DbHelper dbHelper;
    DbMenu dbMenu;

    public CartAdapter2(Context context, ArrayList<CartItem2> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @NotNull
    @Override
    public CartAdapter2.CartHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cart_item, parent, false);
        CartAdapter2.CartHolder cartHolder = new CartAdapter2.CartHolder(listItem);
        return cartHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter2.CartHolder holder, int position) {
        CartItem2 item2=cartItems.get(position);
        String name=item2.getItem_name();
        String price=item2.getItem_price();
        String qnty=item2.getItem_qnty();
        holder.txtName.setText(name);
        holder.txtPrice.setText(String.valueOf(Integer.parseInt(qnty)*Integer.parseInt(price)));
        holder.txtQnty.setText(qnty);
        dbHelper=new DbHelper(context);
        dbMenu=new DbMenu(context);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String j=holder.txtQnty.getText().toString();
                int i;
                i=Integer.parseInt(j);
                i++;
                holder.txtQnty.setText(String.valueOf(i));
                dbHelper.updateQuantity(name,String.valueOf(i));
                dbMenu.updateQuantity(name,String.valueOf(i));

                holder.txtPrice.setText(String.valueOf(Integer.parseInt(holder.txtPrice.getText().toString())+Integer.parseInt(price)));
                Toast.makeText(context, "Added to cart "+i, Toast.LENGTH_SHORT).show();
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String j=holder.txtQnty.getText().toString();
                int i=Integer.parseInt(j);
                i--;
                if(i<1){
                    cartItems.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,cartItems.size());
                    dbHelper.deleteItem(name);
                    dbMenu.deleteItem(name);
                }
                else{
                    dbHelper.updateQuantity(name,String.valueOf(i));
                    dbMenu.updateQuantity(name,String.valueOf(i));
                    holder.txtQnty.setText(String.valueOf(i));
                    holder.txtPrice.setText(String.valueOf(Integer.parseInt(holder.txtPrice.getText().toString())-Integer.parseInt(price)));
                }
                Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView txtName;
        TextView txtQnty;
        FloatingActionButton add,remove;
        TextView txtPrice;
        public CartHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cart_items);
            txtName=itemView.findViewById(R.id.cart_name);
            txtQnty=itemView.findViewById(R.id.cart_qnt);
            add=itemView.findViewById(R.id.total_add);
            remove=itemView.findViewById(R.id.total_remove);
            txtPrice=itemView.findViewById(R.id.cart_prc);
        }
    }
}

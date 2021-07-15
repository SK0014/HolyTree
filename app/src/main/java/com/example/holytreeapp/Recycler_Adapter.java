package com.example.holytreeapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;


public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.view_holder> {

    Context context;
    public ArrayList<membership_types> memberships;
    DbHelper dbHelper;
    DbMember dbMember;
    public Recycler_Adapter(Context context, ArrayList<membership_types> memberships) {
        this.memberships = memberships;
        this.context = context;
    }

    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        view_holder holder = new view_holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter.view_holder holder, int position) {
        membership_types membership = memberships.get(position);
        dbHelper=new DbHelper(context);
        dbMember=new DbMember(context);
        holder.item_text.setText(membership.getDetails());
        holder.prc.setText("Rs "+membership.getPrice());
        holder.heading.setText(membership.getHeading());
        holder.item_img.setImageDrawable(context.getResources().getDrawable(membership.getImage()));
        if(dbMember.hasObject(membership.getHeading())){
            System.out.println("Yes");
            holder.remove.setVisibility(View.VISIBLE);
            holder.cart_btn.setVisibility(View.GONE);
        }
        else{
            System.out.println("No");
        }
        holder.cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                holder.cart_btn.setVisibility(v.GONE);
                holder.remove.setVisibility(v.VISIBLE);
                dbHelper.insertItem(membership.getHeading(),"1",membership.getPrice());
                dbMember.insertItem(membership.getHeading(),"1",membership.getPrice());


            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Remove cart", Toast.LENGTH_SHORT).show();
                holder.cart_btn.setVisibility(v.VISIBLE);
                holder.remove.setVisibility(v.GONE);
                dbHelper.deleteItem(membership.getHeading());
                dbMember.deleteItem(membership.getHeading());

            }
        });
    }

    @Override
    public int getItemCount() {
        return memberships.size();
    }

    public class view_holder extends RecyclerView.ViewHolder {

        TextView item_text, heading,prc;
        ImageView item_img;
        Button cart_btn,remove;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            this.item_text = itemView.findViewById(R.id.item_text);
            this.heading = itemView.findViewById(R.id.heading);
            this.item_img = itemView.findViewById(R.id.item_img);
            this.cart_btn = itemView.findViewById(R.id.cart_btn);
            this.prc=itemView.findViewById(R.id.price);
            this.remove=itemView.findViewById(R.id.remove_btn);
        }
    }
}




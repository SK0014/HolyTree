package com.example.holytreeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.util.logging.Logger.global;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularHolder> {
    Context context;
    ArrayList<Popular> pop;
    DbHelper dbHelper;

    public PopularAdapter(Context context, ArrayList<Popular> pop) {
        this.context = context;
        this.pop = pop;
    }

    @NonNull
    @NotNull
    @Override
    public PopularAdapter.PopularHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.popular_items, parent, false);
        PopularAdapter.PopularHolder popHolder = new PopularAdapter.PopularHolder(listItem);
        return popHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PopularAdapter.PopularHolder holder, int position) {
        Popular popular=pop.get(position);
        dbHelper=new DbHelper(context);
        holder.txt_name.setText(popular.getName());

        holder.img.setImageDrawable(context.getResources().getDrawable(popular.getImage()));
    }

    @Override
    public int getItemCount() {
        return pop.size();
    }

    public class PopularHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView txt_name;
        ImageView img;
        public PopularHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_view);
            txt_name=itemView.findViewById(R.id.name);

            img=itemView.findViewById(R.id.pop_img);
        }
    }
}

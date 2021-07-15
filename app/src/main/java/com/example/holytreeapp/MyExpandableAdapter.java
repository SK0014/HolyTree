package com.example.holytreeapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String>grpList;
    Map<String, ArrayList<Food>>coll;
    DbHelper dbHelper;
    DbMenu dbMenu;
    public MyExpandableAdapter(Context context, ArrayList<String> grpList, Map<String, ArrayList<Food>> coll) {
        this.context = context;
        this.grpList = grpList;
        this.coll = coll;
    }

    @Override
    public int getGroupCount() {
        System.out.println("Size of grp is "+coll.size());
        return coll.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        System.out.println("Size is "+coll.get(grpList.get(groupPosition)).size());
        return coll.get(grpList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return grpList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return coll.get(grpList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String name=getGroup(groupPosition).toString();
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.menu_category,null);
        }
        TextView item=convertView.findViewById(R.id.category_name);
        CardView card=convertView.findViewById(R.id.category_card);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Food food= (Food) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.menu_layout,null);
        }
        dbHelper=new DbHelper(context);
        dbMenu=new DbMenu(context);
        TextView qnt=convertView.findViewById(R.id.edt_qnt);
        CardView cd=convertView.findViewById(R.id.card_item);
        ImageView img=convertView.findViewById(R.id.img_food);
        TextView txtDet=convertView.findViewById(R.id.food_dtl);
        TextView txtPrc=convertView.findViewById(R.id.txt_prc);
        TextView txtName=convertView.findViewById(R.id.name);
        txtName.setText(food.getName());
        txtPrc.setText("Rs "+food.getPrice());
        String a=dbMenu.getQuantity(food.getName());
        if(a==null){
            qnt.setText("0");
        }
        else{
            qnt.setText(a);
        }

        FloatingActionButton btn_add=convertView.findViewById(R.id.cart_add);
        txtDet.setText(food.getDetails());
        FloatingActionButton btn_remove=convertView.findViewById(R.id.cart_remove);
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String j=qnt.getText().toString();
                int i=Integer.parseInt(j);
                if(i<1){
                    btn_remove.setEnabled(false);
                    dbHelper.deleteItem(food.getName());
                    dbMenu.deleteItem(food.getName());
                }
                else{
                    i--;
                    food.setQnty(String.valueOf(i));
                    qnt.setText(food.getQnty());
                    dbHelper.updateQuantity(food.getName(),String.valueOf(i));
                    dbMenu.updateQuantity(food.getName(),String.valueOf(i));
                    if(i==0){
                        btn_remove.setEnabled(false);
                        dbHelper.deleteItem(food.getName());
                        dbMenu.deleteItem(food.getName());
                    }
                }
                food.setQnty(qnt.getText().toString());
                Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String j=qnt.getText().toString();
                int i=0;
                i=Integer.parseInt(j);
                i++;
                if(i==1){
                    dbMenu.insertItem(food.getName(),String.valueOf(i),food.getPrice());
                    btn_remove.setEnabled(true);
                    dbHelper.insertItem(food.getName(),String.valueOf(i),food.getPrice());
                }
                else {
                    dbMenu.updateQuantity(food.getName(),String.valueOf(i));
                    dbHelper.updateQuantity(food.getName(),String.valueOf(i));
                }
                food.setQnty(String.valueOf(i));
                qnt.setText(food.getQnty());
                food.setQnty(qnt.getText().toString());

                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

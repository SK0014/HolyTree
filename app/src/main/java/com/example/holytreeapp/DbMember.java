package com.example.holytreeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbMember extends SQLiteOpenHelper {
    public static final String dataBase_name="MyMemberDatabase";
    public static final String tableName="member";
    public static final String table_Column_Name="name";
    public static final String table_Column_Qnty="quantity";
    public static final String table_Column_Price="price";
    ArrayList<CartItem> cart_db;
    public DbMember(@Nullable Context context) {
        super(context, dataBase_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+" (name text primary key, quantity text, price text ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }
    public void insertItem(String name, String quantity, String price){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(table_Column_Name,name);
        contentValues.put(table_Column_Qnty,quantity);
        contentValues.put(table_Column_Price,price);
        db.insert(tableName,null,contentValues);

    }
    public void updateQuantity(String name, String qnty){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(table_Column_Qnty,qnty);
        db.update(tableName,cv,"name = ?",new String[]{name});
    }
    public boolean hasObject(String id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+tableName,null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            if(res.getString(res.getColumnIndex(table_Column_Name)).equals(id)){
                return true;
            }
            res.moveToNext();
        }
        return false;
    }


    public Integer deleteItem(String name){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(tableName,"name = ?",new String[]{name});
    }
    public int getSumValue() {
        int total=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+tableName,null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            total+=Integer.parseInt(res.getString(res.getColumnIndex(table_Column_Price)))*Integer.parseInt(res.getString(res.getColumnIndex(table_Column_Qnty)));
            res.moveToNext();
        }
        return total;
    }
    public ArrayList<CartItem> getAlldata(){
        cart_db=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+tableName,null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            cart_db.add(new CartItem(0,res.getString(res.getColumnIndex(table_Column_Name)),res.getString(res.getColumnIndex(table_Column_Price)),res.getString(res.getColumnIndex(table_Column_Qnty))));
            res.moveToNext();
        }
        return cart_db;
    }
}

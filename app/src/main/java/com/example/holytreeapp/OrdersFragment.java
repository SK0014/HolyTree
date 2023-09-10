package com.example.holytreeapp;

import android.app.ActionBar;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersFragment extends Fragment {
    ArrayList<String>grplist;
    Map<String,ArrayList<Food>> foodColl;
    ExpandableListView expList;
    ExpandableListAdapter expAdap;
    FirebaseFirestore fb;
    DbHelper dbHelper;
    private static final String TAG = OrdersFragment.class.getSimpleName();
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_orders,container,false);
        grplist=new ArrayList<>();
        fb=FirebaseFirestore.getInstance();

        createGrpList();
        createColl();
        expList=view.findViewById(R.id.category);
        expAdap=new MyExpandableAdapter(getContext(),grplist,foodColl);
        expList.setAdapter(expAdap);
        expList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPos=1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPos!=-1&&groupPosition!=lastExpandedPos){
                    expList.collapseGroup(lastExpandedPos);
                }
                lastExpandedPos=groupPosition;
            }
        });
        expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected=expAdap.getChild(groupPosition,childPosition).toString();
                Toast.makeText(getContext(), "Selected "+selected, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        return view;
    }

    private void createColl() {
        foodColl=new HashMap<String, ArrayList<Food>>();
        int i=0;
        for(String grp:grplist){
            ArrayList<Food>chllist;
            chllist=new ArrayList<Food>();
            if(grp.equals("Burgers")){
                fb.collection("Burgers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                fb.collection("Burgers").document(doc.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        FoodModel foodModel=documentSnapshot.toObject(FoodModel.class);
                                        String details=foodModel.getDetails();
                                        String price=foodModel.getPrice();
                                        chllist.add(new Food(doc.getId(),details,price,"0"));
                                    }
                                });
                            }

                        }
                        else{
                            Log.d(TAG,"Error msg: ",task.getException());
                        }
                    }
                });
            }
            else if(grp.equals("Beverages")){
                fb.collection("Shakes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                fb.collection("Shakes").document(doc.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                        FoodModel foodModel=documentSnapshot.toObject(FoodModel.class);
                                        String details=foodModel.getDetails();
                                        String price=foodModel.getPrice();
                                        chllist.add(new Food(doc.getId(),details,price,"0"));
                                    }
                                });
                            }

                        }
                        else{
                            Log.d(TAG,"Error msg: ",task.getException());
                        }
                    }
                });
            }
            else if(grp.equals("Main Course")){
                fb.collection("Indian Curries ").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                fb.collection("Indian Curries ").document(doc.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                        FoodModel foodModel=documentSnapshot.toObject(FoodModel.class);
                                        String details=foodModel.getDetails();
                                        String price=foodModel.getPrice();
                                        chllist.add(new Food(doc.getId(),details,price,"0"));
                                    }
                                });
                            }

                        }
                        else{
                            Log.d(TAG,"Error msg: ",task.getException());
                        }
                    }
                });
            }
            else if(grp.equals("Desserts")){
                fb.collection("Dessert ").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                fb.collection("Dessert ").document(doc.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                        FoodModel foodModel=documentSnapshot.toObject(FoodModel.class);
                                        String details=foodModel.getDetails();
                                        String price=foodModel.getPrice();
                                        chllist.add(new Food(doc.getId(),details,price,"0"));
                                    }
                                });
                            }

                        }
                        else{
                            Log.d(TAG,"Error msg: ",task.getException());
                        }
                    }
                });
            }
           else {
                fb.collection("Smoothies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                fb.collection("Smoothies").document(doc.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        FoodModel foodModel=documentSnapshot.toObject(FoodModel.class);
                                        String details=foodModel.getDetails();
                                        String price=foodModel.getPrice();
                                        chllist.add(new Food(doc.getId(),details,price,"0"));
                                    }
                                });
                            }

                        }
                        else{
                            Log.d(TAG,"Error msg: ",task.getException());
                        }
                    }
                });
            }
            foodColl.put(grp, chllist);
        }
    }


    private void createGrpList() {
        grplist=new ArrayList<>();
        grplist.add("Burgers");
        grplist.add("Main Course");
        grplist.add("Beverages");
        grplist.add("Desserts");
        grplist.add("Smoothies");

    }
}

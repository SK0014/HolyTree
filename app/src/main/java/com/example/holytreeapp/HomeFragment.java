package com.example.holytreeapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<Popular> arr;
    PopularAdapter popAdap;
    Toolbar toolbar;
    FirebaseAuth mauth;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=view.findViewById(R.id.popular);
        arr=new ArrayList<>();
        mauth=FirebaseAuth.getInstance();
        arr.add(new Popular("Dal Makhani",R.drawable.dal));
        arr.add(new Popular("Family Platter",R.drawable.family_platter));
        arr.add(new Popular("Naanza",R.drawable.naanza));
        arr.add(new Popular("4 Cheese Pizza",R.drawable.four_cheese));
        popAdap=new PopularAdapter(getContext(),arr);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(popAdap);
        ImageSlider imageSlider=view.findViewById(R.id.slider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://storage.googleapis.com/holy-tree-73664.appspot.com/20210105041137_IMG_7234-01.jpeg","The Flavours of your wish", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://storage.googleapis.com/holy-tree-73664.appspot.com/20210105044446_IMG_7258-01.jpeg","Tasty Hot and Hygienic", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://storage.googleapis.com/holy-tree-73664.appspot.com/20210105050527_IMG_7283-01.jpeg","Your Hunger Partner", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://storage.googleapis.com/holy-tree-73664.appspot.com/pic.jpeg","Good Food for Good Moments", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://storage.googleapis.com/holy-tree-73664.appspot.com/pic2.jpg","if Reading is to mind then Detox is to body", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);
        return view;

    }
}

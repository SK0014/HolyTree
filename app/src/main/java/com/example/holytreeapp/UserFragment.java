package com.example.holytreeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class UserFragment extends Fragment {
    Button btn,btn_up;
    FirebaseAuth mauth;
    TextView txt_name;
    TextView txt_mail;
    TextView txt_phone;
    FirebaseFirestore fb;
     GoogleSignInClient mGoogleSignInClient;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
       // int i=this.getArguments().getInt("no");
        mauth=FirebaseAuth.getInstance();
        String a;
        if(mauth.getCurrentUser().getEmail()==null){
            a=mauth.getCurrentUser().getPhoneNumber().substring(3);
        }
        else{
            a=mauth.getCurrentUser().getEmail();
        }
        txt_name=view.findViewById(R.id.txt_name);
        txt_mail=view.findViewById(R.id.txt_gmail);
        txt_phone=view.findViewById(R.id.txt_mob);
        btn=view.findViewById(R.id.log_out);
        btn_up=view.findViewById(R.id.btn_update);
        fb=FirebaseFirestore.getInstance();
        DocumentReference documentReference;
        fb.collection("Users").document(a).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserModel userModel=documentSnapshot.toObject(UserModel.class);
                String mail=userModel.getMail();
                String name=userModel.getFname();
                String phone= userModel.getPhone();
                txt_mail.setText(mail);
                txt_name.setText(name);
                txt_phone.setText(phone);
            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ProfileUpdate.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mauth.signOut();
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });

        return view;
    }
}

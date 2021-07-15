package com.example.holytreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class ProfileUpdate extends AppCompatActivity {
    EditText name,mail,phone,address;
    Button update;
    FirebaseFirestore fb;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        fb=FirebaseFirestore.getInstance();
        getSupportActionBar().hide();
        phone=findViewById(R.id.upd_mob_no);
        update=findViewById(R.id.up_bt);
        address=findViewById(R.id.edt_new_add);
        name=findViewById(R.id.upd_name);
        mail=findViewById(R.id.upd_email);
        mauth=FirebaseAuth.getInstance();
        String a;
        if(mauth.getCurrentUser().getEmail()==null){
            a=mauth.getCurrentUser().getPhoneNumber().substring(3);
        }
        else{
            a=mauth.getCurrentUser().getEmail();
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.collection("Users").document(a).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc=task.getResult();
                            if(doc.exists()){
                                fb.collection("Users").document(a).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        UserModel userModel=documentSnapshot.toObject(UserModel.class);
                                        userModel.setPhone(phone.getText().toString());
                                        userModel.setFname(name.getText().toString());
                                        userModel.setMail(mail.getText().toString());
                                        userModel.setAddress(address.getText().toString());
                                        fb.collection("Users").document(a).set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(ProfileUpdate.this, "Data has been Updated", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(ProfileUpdate.this,MainActivity.class));
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });

    }
}
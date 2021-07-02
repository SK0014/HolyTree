package com.example.holytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText edtName;
    EditText edtmail;
    EditText edtadd;
    EditText edtcno;
    Button btnsub;
    FirebaseFirestore fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName=findViewById(R.id.edt_name);
        edtmail=findViewById(R.id.edt_email);
        edtadd=findViewById(R.id.edt_add);
        edtcno=findViewById(R.id.edt_no);
        btnsub=findViewById(R.id.btn_submit);
        fb=FirebaseFirestore.getInstance();
          edtcno.setText(getIntent().getStringExtra("mob"));
       btnsub.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!edtcno.getText().toString().isEmpty()&&!edtName.getText().toString().isEmpty()&&!edtadd.getText().toString().isEmpty()
                       &&!edtmail.getText().toString().isEmpty()){
                   UserModel userModel=new UserModel(edtName.getText().toString(),edtcno.getText().toString(),edtadd.getText().toString(),edtmail.getText().toString());
                   DocumentReference documentReference=fb.collection("Users").document(edtcno.getText().toString());
                   Map<String,Object> user_data=new HashMap<>();
                   String name=edtName.getText().toString();
                   String mail=edtmail.getText().toString();
                   String add=edtadd.getText().toString();
                   String no=edtcno.getText().toString();
                   user_data.put("fname",name);
                   user_data.put("mail",mail);
                   user_data.put("address",add);
                   user_data.put("phone",no);
                   documentReference.set(user_data).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           Toast.makeText(SignUp.this, "Successfully Signed up", Toast.LENGTH_SHORT).show();
                           Intent i=new Intent(SignUp.this,after_otp_verification.class);
                           startActivity(i);
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull @NotNull Exception e) {
                           Toast.makeText(SignUp.this, "Some error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });


               }
               else{
                   Toast.makeText(SignUp.this, "Please enter all details", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

}
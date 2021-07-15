package com.example.holytreeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class get_number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);
        //getSupportActionBar().hide();

        final Button get_otp_btn = findViewById(R.id.get_otp_btn);
        final EditText input_number = findViewById(R.id.input_number);
        final ProgressBar progressBar = findViewById(R.id.progressbar1);
        String cno=getIntent().getStringExtra("cno");
        input_number.setText(cno);


        get_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (input_number.getText().toString().trim().isEmpty()) {
                    Toast.makeText(get_number.this, "Please enter a mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.GONE);
                get_otp_btn.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + input_number.getText().toString(), 60, TimeUnit.SECONDS, get_number.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.VISIBLE);
                                get_otp_btn.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.VISIBLE);
                                get_otp_btn.setVisibility(View.INVISIBLE);
                                Toast.makeText(get_number.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.VISIBLE);
                                get_otp_btn.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(getApplicationContext(), verify_otp.class);
                                intent.putExtra("mobile", input_number.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
                            }
                        });

            }
        });
    }
}
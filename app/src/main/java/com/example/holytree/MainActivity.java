package com.example.holytree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_cno;
    ImageButton btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_cno=findViewById(R.id.edt_no);
        btn_submit=(ImageButton)findViewById(R.id.btn_login);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_cno.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter Contact details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(getApplicationContext(),get_number.class);
                    i.putExtra("cno",edt_cno.getText().toString());
                    startActivity(i);
                }

            }
        });
    }
}
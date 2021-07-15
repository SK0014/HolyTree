package com.example.holytreeapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainApp extends AppCompatActivity {
    BottomNavigationView bottomNav;
    ArrayList<Food> foods;
    MyAdapter myAdapter;
    Button btn;
    RecyclerView recyclerView;
    FirebaseAuth mauth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(MainApp.this,AddCart.class);
                startActivity(i);
                return true;
            }
        });
        return super.onOptionsItemSelected(item);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        bottomNav=findViewById(R.id.bottom_navigation);
        int i=getIntent().getIntExtra("key",0);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        mauth=FirebaseAuth.getInstance();
        View v = inflator.inflate(R.layout.titleview, null);
       /* String a;
        if(getIntent().getStringExtra("mb")!=null){
            a=getIntent().getStringExtra("mb");
        }
        else{
            a=getIntent().getStringExtra("mob");
        }*/

        ((TextView)v.findViewById(R.id.title)).setText(this.getTitle());

        actionBar.setCustomView(v);
        actionBar.setBackgroundDrawable(getDrawable(R.drawable.grad));
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,new HomeFragment()).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                Fragment selected=null;
                switch (item.getItemId()){
                    case R.id.navHome:
                        selected=new HomeFragment();
                        break;
                    case R.id.navMem:
                        selected=new MemberFragment();
                        break;
                    case R.id.navOrder:
                        selected=new OrdersFragment();
                        break;
                    case R.id.navUser:
                        selected=new UserFragment();
                        /*Bundle bundle=new Bundle();
                        bundle.putInt("no",1);
                        selected.setArguments(bundle);*/
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont,selected).commit();
                return true;
            }
        });

    }
}
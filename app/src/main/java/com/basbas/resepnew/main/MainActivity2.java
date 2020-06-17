package com.basbas.resepnew.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basbas.resepnew.AddResepActivity;
import com.basbas.resepnew.Constant;
import com.basbas.resepnew.R;
import com.basbas.resepnew.model.ResepItem;
import com.basbas.resepnew.model.ResponseGetAllDataResep;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.pref.SessionPref;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {


    RecyclerView listresep;
    RecyclerView.LayoutManager layoutManager;
    //membuat variabel datamakan menggunakn List (untuk menampung data)
    List<ResepItem> dataresep ;
    FloatingActionButton addData;
    ProgressBar progressBar;
    SessionPref sessionPref;
    MainViewmodel mainViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionPref = new SessionPref(this);
        sessionPref.checkLoginMain();
        //inisialis
        progressBar = findViewById(R.id.progress_circular);
        listresep = findViewById(R.id.listresep);
        addData = findViewById(R.id.addData);
        listresep.setLayoutManager(new LinearLayoutManager(this));

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, AddResepActivity.class));
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        mainViewmodel = new ViewModelProvider(this).get(MainViewmodel.class);
        mainViewmodel.init();
        mainViewmodel.getDataResep().observe(this, data -> {
            dataresep.addAll(data.getResep());
            if (data.getResep() != null){
                progressBar.setVisibility(View.VISIBLE);
                RecylerViewAdapter adapter = new RecylerViewAdapter(MainActivity2.this, dataresep);
                listresep.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listresep.setItemAnimator(new DefaultItemAnimator());
                listresep.setNestedScrollingEnabled(true);
            } else {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity2.this, "Gagal dapatkan Data", Toast.LENGTH_SHORT).show();

            }


        });


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd = item.getItemId();
        if (idd==R.id.logout) {
            sessionPref.logoutUser();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }


}

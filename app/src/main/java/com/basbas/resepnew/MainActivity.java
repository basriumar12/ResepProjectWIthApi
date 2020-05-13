package com.basbas.resepnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basbas.resepnew.model.ResepItem;
import com.basbas.resepnew.model.ResponseGetAllDataResep;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.pref.SessionPref;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView listresep;
    RecyclerView.LayoutManager layoutManager;
    //membuat variabel datamakan menggunakn List (untuk menampung data)
    List<ResepItem> dataresep;
    FloatingActionButton addData;
    ProgressBar progressBar;
    SessionPref sessionPref;
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
        getDataResep();
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddResepActivity.class));
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

    private void getDataResep() {
        progressBar.setVisibility(View.VISIBLE);
        //inisialisasi retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL) //ke url java MyCOnstant
                .addConverterFactory(GsonConverterFactory.create())//diconvert
                .build();//dibuild

        RestApi api = retrofit.create(RestApi.class);
        //ngecal
        Call<ResponseGetAllDataResep> modelResepCall = api.getDataResep();
        modelResepCall.enqueue(new Callback<ResponseGetAllDataResep>() {
            @Override
            public void onResponse(Call<ResponseGetAllDataResep> call, Response<ResponseGetAllDataResep> response) {
                Log.e("TAG","data berhasil "+new Gson().toJson(response.body().getResep()));
                String pesan = response.body().getPesan();
                String sukses = response.body().getSukses();

                if (sukses.equals("true")) {
                    progressBar.setVisibility(View.GONE);
                    dataresep = response.body().getResep();
                    tampilResep();

                } else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Gagal dapatkan Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAllDataResep> call, Throwable t) {
                Log.e("TAG","error message"+t.getMessage());

                Toast.makeText(MainActivity.this, "Gagal dapatkan Data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    private void tampilResep() {
        String items[] = new String[dataresep.size()];
        for (int i = 0; i < dataresep.size(); i++) {
            items[i] = dataresep.get(i).getNamaResep();
        }
        RecylerViewAdapter adapter = new RecylerViewAdapter(MainActivity.this, dataresep);
        listresep.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}

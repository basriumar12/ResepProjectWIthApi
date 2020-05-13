package com.basbas.resepnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailResepActivity extends AppCompatActivity {

    ImageView imgmakanan;
    TextView txtnama;
    TextView txtdetail;
    String id,nama,detail,gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);

        imgmakanan = findViewById(R.id.imgmakanan);
        txtnama= findViewById(R.id.txtnama);
        txtdetail = findViewById(R.id.txtdetail);
        //tambahan
        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nm");
        detail = getIntent().getStringExtra("i");
        gambar = getIntent().getStringExtra("gb");

        txtnama.setText(nama);
        // Hehe
        txtdetail.setText(detail);
        Picasso.get().load(gambar).
                error(R.mipmap.ic_launcher).into(imgmakanan);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd = item.getItemId();
        if (idd==R.id.idUpdateDelete){
            Intent intent = new Intent(this, AddResepActivity.class);
            intent.putExtra("ID",id);
            intent.putExtra("NAMA",nama);
            intent.putExtra("DETAIL",detail);
            intent.putExtra("GAMBAR",gambar);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

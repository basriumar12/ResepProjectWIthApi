package com.basbas.resepnew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basbas.resepnew.model.ResepItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyHolder> {
    //buat variabel
    Context con;
    List<ResepItem> data_resep;
    //buat constructor dari variabel diatas
    public RecylerViewAdapter(Context con, List<ResepItem> data_resep) {
        this.con = con;
        this.data_resep = data_resep;
    }

    @Override
    public RecylerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilannamaresep,parent,false);
        MyHolder holder = new MyHolder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapter.MyHolder holder, final int position) {
        holder.txtnama.setText(data_resep.get(position).getNamaResep());
        if (data_resep.get(position).getGambar().isEmpty()){
            holder.imgMakanan.setImageResource(R.drawable.img_noavailable);
        } else {
            Picasso.get().load(data_resep.get(position).getGambar()).error(R.drawable.img_noavailable).into(holder.imgMakanan);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kirim = new Intent(con, DetailResepActivity.class);
                //tambaahan
                kirim.putExtra("id",data_resep.get(position).getIdResep());
                kirim.putExtra("nm",data_resep.get(position).getNamaResep());
                kirim.putExtra("gb",data_resep.get(position).getGambar());
                kirim.putExtra("i",data_resep.get(position).getDetail());
                con.startActivity(kirim);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data_resep.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgMakanan ;
        TextView txtnama;
        public MyHolder(View itemView) {
            super(itemView);
            imgMakanan = (ImageView) itemView.findViewById(R.id.imgmakanan);
            txtnama = (TextView) itemView.findViewById(R.id.txtnama);
        }
    }
}

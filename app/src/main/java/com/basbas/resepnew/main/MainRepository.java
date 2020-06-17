package com.basbas.resepnew.main;

import androidx.lifecycle.MutableLiveData;

import com.basbas.resepnew.model.ResepItem;
import com.basbas.resepnew.model.ResponseGetAllDataResep;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.network.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    //buat nama variabel
    public static MainRepository mainRepository;


    public static MainRepository getInstance(){
        if (mainRepository == null){
            mainRepository = new MainRepository();

        }
        return mainRepository;
    }

    private RestApi restApi;

    public MainRepository(){
        restApi = RetroServer.getClient().create(RestApi.class);
    }

    public MutableLiveData<ResponseGetAllDataResep> getData() {

        final MutableLiveData<ResponseGetAllDataResep> resepItemMutableLiveData = new MutableLiveData<>();
        final MutableLiveData<String> error = new MutableLiveData<>();

        restApi.getDataResep().enqueue(new Callback<ResponseGetAllDataResep>() {
            @Override
            public void onResponse(Call<ResponseGetAllDataResep> call, Response<ResponseGetAllDataResep> response) {
                if (response.isSuccessful()){
                    resepItemMutableLiveData.setValue(response.body());
                } else {
                    error.setValue("Gagal dapatkan data");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAllDataResep> call, Throwable t) {
                resepItemMutableLiveData.setValue(null);
                error.setValue("Gagal dapatkan data");
            }
        });

        return resepItemMutableLiveData;
    }


}

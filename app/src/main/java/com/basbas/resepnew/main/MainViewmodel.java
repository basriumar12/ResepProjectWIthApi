package com.basbas.resepnew.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basbas.resepnew.model.ResepItem;
import com.basbas.resepnew.model.ResponseGetAllDataResep;

import java.util.ArrayList;

public class MainViewmodel extends ViewModel {

    private MutableLiveData<ResponseGetAllDataResep> mutableLiveData;
    private MutableLiveData<String> error;
    private MainRepository mainRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }

        mainRepository=  MainRepository.getInstance();
        mutableLiveData = mainRepository.getData();

    }

    public LiveData<ResponseGetAllDataResep> getDataResep(){
        return mutableLiveData;
    }


}


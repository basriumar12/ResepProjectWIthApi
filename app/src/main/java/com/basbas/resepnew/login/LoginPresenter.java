package com.basbas.resepnew.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.basbas.resepnew.MainActivity;
import com.basbas.resepnew.base.Presenter;
import com.basbas.resepnew.model.ResponseData;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.network.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements Presenter<LoginVIew> {

    LoginVIew loginVIew;

    @Override
    public void onAttach(LoginVIew view) {
        loginVIew = view;
    }

    @Override
    public void onDetach() {
        loginVIew = null;
    }

    //method login
    void Login(final String email, String pass) {
        if (email.isEmpty() || pass.isEmpty()) {
            loginVIew.showMessage("Email atau Password tidak bisa kosong");

        } else {
            loginVIew.showLoading();
            RestApi api = RetroServer.getClient().create(RestApi.class);
            Call<ResponseData> userRegister = api.userLogin(email, pass);
            userRegister.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    Log.e("TAG", "response " + response.body().getPesan());

                    int kode = response.body().getKode();
                    if (kode == 1) {
                        loginVIew.hideLoading();
                        loginVIew.showSukses(email);
                    } else {
                        loginVIew.hideLoading();
                        loginVIew.showMessage("Gagal Login");
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    loginVIew.showMessage("Gagal Login");
                    loginVIew.hideLoading();

                }
            });
        }
    }


}

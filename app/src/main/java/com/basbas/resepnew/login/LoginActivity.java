package com.basbas.resepnew.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basbas.resepnew.main.MainActivity;
import com.basbas.resepnew.R;
import com.basbas.resepnew.RegisterActivity;
import com.basbas.resepnew.main.MainActivity2;
import com.basbas.resepnew.model.ResponseData;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.network.RetroServer;
import com.basbas.resepnew.pref.SessionPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginVIew {

    EditText edtEmail, edtPass;
    Button btnLogin;
    TextView tvRegister;
    ProgressBar pdLogin;
    SessionPref sessionPref;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter();
        sessionPref = new SessionPref(LoginActivity.this);
        sessionPref.checkLogin();
        edtEmail = findViewById(R.id.edt_email_login);
        edtPass = findViewById(R.id.edt_pass_login);
        tvRegister = findViewById(R.id.tv_register_now);
        btnLogin = findViewById(R.id.btn_login);
        pdLogin = findViewById(R.id.pd_login);


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        onAttachVIew();


    }

    private void loginAction() {
        final String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Data wajib diisi", Toast.LENGTH_SHORT).show();
        } else {
            pdLogin.setVisibility(View.VISIBLE);
            RestApi api = RetroServer.getClient().create(RestApi.class);
            Call<ResponseData> userRegister = api.userLogin(email, pass);
            userRegister.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    Log.e("TAG", "response " + response.body().getPesan());
                    Log.e("TAG", "response " + response.body().getKode());
                    int kode = response.body().getKode();
                    if (kode == 1) {
                        sessionPref.createLoginSession(email);
                        pdLogin.setVisibility(View.GONE);
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();

                        pdLogin.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    Log.e("TAG", "errror " + t.getMessage());
                    pdLogin.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    @Override
    public void showLoading() {
        pdLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pdLogin.setVisibility(View.GONE);
    }

    @Override
    public void showSukses(String email) {
        sessionPref.createLoginSession(email);
        pdLogin.setVisibility(View.GONE);
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAttachVIew() {
        loginPresenter.onAttach(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                loginPresenter.Login(
                        email, pass
                );
            }
        });
    }

    @Override
    public void onDetachView() {
        loginPresenter.onDetach();
    }
}

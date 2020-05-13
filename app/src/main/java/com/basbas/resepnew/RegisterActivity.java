package com.basbas.resepnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basbas.resepnew.model.ResponseData;
import com.basbas.resepnew.network.RestApi;
import com.basbas.resepnew.network.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText name, jk, email, username, password, image;
    Button register;
    ProgressBar pbRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.edt_name);
        jk = findViewById(R.id.edt_jk);
        email = findViewById(R.id.edt_email);
        image = findViewById(R.id.edt_image);
        password = findViewById(R.id.edt_password);
        username = findViewById(R.id.edt_username);
        register = findViewById(R.id.btn_register);
        pbRegister = findViewById(R.id.pb_register);

        register();


    }

    private void register() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueName = name.getText().toString();
                String valueJk = jk.getText().toString();
                String valueEmail = email.getText().toString();
                String valueUsername = username.getText().toString();
                String valueImage = image.getText().toString();
                String valuePassword = password.getText().toString();

                //validasi
                if (valueName.isEmpty() ||
                    valueEmail.isEmpty() ||
                    valueImage.isEmpty() ||
                    valueJk.isEmpty() ||
                    valuePassword.isEmpty() ||
                    valueUsername.isEmpty()
                ){
                    Toast.makeText(RegisterActivity.this, "Data tidak bisa kosong",
                            Toast.LENGTH_SHORT).show();
                } else {
                    pbRegister.setVisibility(View.VISIBLE);
                    RestApi api = RetroServer.getClient().create(RestApi.class);
                    Call<ResponseData> userRegister = api.userRegister(
                            valueName, valueJk,valueImage,valueEmail,valueUsername,valuePassword
                    );

                    userRegister.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            int kode = response.body().getKode();

                            if (kode == 1){
                                pbRegister.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "User Berhasil Register", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,
                                        LoginActivity.class
                                        ));
                                finish();
                            } else {

                                pbRegister.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "User Gagal Register", Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {

                            pbRegister.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "User Gagal Register", Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
        });

    }
}

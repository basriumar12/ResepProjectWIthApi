package com.basbas.resepnew.network;

import com.basbas.resepnew.model.ResepItem;
import com.basbas.resepnew.model.ResponseData;
import com.basbas.resepnew.model.ResponseGetAllDataResep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @GET("getdataresep.php")
    Call <ResponseGetAllDataResep> getDataResep();

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseData> insertData(@Field("nama_resep") String nama,
                                   @Field("detail") String detail,
                                   @Field("gambar") String gambar);

    @FormUrlEncoded
    @POST("user_register.php")
    Call<ResponseData> userRegister(@Field("name") String nama,
                                   @Field("jk") String jk,
                                   @Field("image") String image,
                                   @Field("email") String email,
                                   @Field("username") String username,
                                   @Field("password") String password

    );

    @FormUrlEncoded
    @POST("user_login.php")
    Call<ResponseData> userLogin(
                                   @Field("email") String email,
                                   @Field("password") String password

    );
    //update menggunakan 3 parameter
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseData> updateData( @Field("id_resep") String id,
                                    @Field("nama_resep") String nama,
                                    @Field("detail") String detail,
                                    @Field("gambar") String gambar);
    //delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseData> deleteData(@Field("id_resep") String id);
}

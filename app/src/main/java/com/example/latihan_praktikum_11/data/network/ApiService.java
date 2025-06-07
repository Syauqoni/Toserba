package com.example.latihan_praktikum_11.data.network;

import com.example.latihan_praktikum_11.data.entity.Produk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("products")
    Call<List<Produk>> getProduk();

    @GET("products/search")
    Call<List<Produk>> searchProduk(@Query("query") String query);
}

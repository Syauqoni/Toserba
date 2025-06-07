package com.example.latihan_praktikum_11.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.latihan_praktikum_11.data.database.AppDatabase;
import com.example.latihan_praktikum_11.data.database.ProdukDao;
import com.example.latihan_praktikum_11.data.entity.Produk;
import com.example.latihan_praktikum_11.data.network.ApiService;
import com.example.latihan_praktikum_11.data.network.RetrofitClient;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukRepository {
    private ProdukDao produkDao;
    private ApiService apiService;
    private Executor executor;

    public ProdukRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        produkDao = db.produkDao();
        apiService = RetrofitClient.getClient().create(ApiService.class);
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Produk>> getAllProduk() {
        fetchFromApi();
        return produkDao.getAllProduk(); // data dari Room
    }

    private void fetchFromApi() {
        apiService.getProduk().enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executor.execute(() -> produkDao.insertAll(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable t) {
                Log.e("ProdukRepository", "API Error: " + t.getMessage());
            }
        });
    }

    public LiveData<List<Produk>> searchProduk(String keyword) {
        return produkDao.searchProduk(keyword);
    }

    public void tambahProduk(Produk produk) {
        executor.execute(() -> produkDao.insert(produk));
    }

}
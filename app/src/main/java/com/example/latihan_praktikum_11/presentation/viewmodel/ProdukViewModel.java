package com.example.latihan_praktikum_11.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.latihan_praktikum_11.data.entity.Produk;
import com.example.latihan_praktikum_11.data.repository.ProdukRepository;

import java.util.List;

public class ProdukViewModel extends AndroidViewModel {
    private ProdukRepository repository;
    private LiveData<List<Produk>> produkList;

    public ProdukViewModel(@NonNull Application application) {
        super(application);
        repository = new ProdukRepository(application);
        produkList = repository.getAllProduk();
    }

    public ProdukViewModel(ProdukRepository repository) {
        super(new Application());
        this.repository = repository;
        this.produkList = repository.getAllProduk();
    }

    public LiveData<List<Produk>> getAllProduk() {
        return produkList != null ? produkList : repository.getAllProduk();
    }

    public LiveData<List<Produk>> searchProduk(String keyword) {
        return repository.searchProduk(keyword);
    }

    public void tambahProduk(Produk produk) {
        repository.tambahProduk(produk);
    }

    public void setRepository(ProdukRepository repository) {
        this.repository = repository;
        this.produkList = repository.getAllProduk();
    }
}

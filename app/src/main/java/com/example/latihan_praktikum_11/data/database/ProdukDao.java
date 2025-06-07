package com.example.latihan_praktikum_11.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.latihan_praktikum_11.data.entity.Produk;

import java.util.List;

@Dao
public interface ProdukDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Produk> produkList);

    @Query("SELECT * FROM produk WHERE title LIKE '%' || :query || '%'")
    LiveData<List<Produk>> searchProduk(String query);

    @Query("SELECT * FROM produk")
    LiveData<List<Produk>> getAllProduk();

    @Insert
    void insert(Produk produk);

}





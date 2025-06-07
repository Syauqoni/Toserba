package com.example.latihan_praktikum_11.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.latihan_praktikum_11.data.entity.Produk;

@Database(entities = {Produk.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProdukDao produkDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "produk_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}



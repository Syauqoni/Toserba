package com.example.latihan_praktikum_11.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produk")
public class Produk {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private double price;
    private String image;

    public Produk(int id, String title, double price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    // Getter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getImage() { return image; }

    public void setId(int id) {
        this.id = id;
    }
}


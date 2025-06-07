package com.example.latihan_praktikum_11.presentation.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihan_praktikum_11.R;
import com.example.latihan_praktikum_11.data.entity.Produk;
import com.example.latihan_praktikum_11.presentation.adapter.ProdukAdapter;
import com.example.latihan_praktikum_11.presentation.viewmodel.ProdukViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KontenFragment extends Fragment {
    private static final int REQUEST_IMAGE_PICK = 101;
    private ImageView selectedImageView;

    private ProdukViewModel produkViewModel;
    private ProdukAdapter produkAdapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    public KontenFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_konten, container, false);

        produkViewModel = new ViewModelProvider(this).get(ProdukViewModel.class);
        recyclerView = rootView.findViewById(R.id.rvProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        produkAdapter = new ProdukAdapter(new ArrayList<>());
        recyclerView.setAdapter(produkAdapter);

        searchView = rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    produkViewModel.getAllProduk().observe(getViewLifecycleOwner(), produkList -> {
                        if (produkList != null) {
                            produkAdapter.setProdukList(produkList);
                        }
                    });
                } else {
                    produkViewModel.searchProduk(newText).observe(getViewLifecycleOwner(), produkList -> {
                        if (produkList != null && !produkList.isEmpty()) {
                            produkAdapter.setProdukList(produkList);
                        } else {
                            Log.d("KontenFragment", "Produk tidak ditemukan");
                        }
                    });
                }
                return true;
            }
        });

        produkViewModel.getAllProduk().observe(getViewLifecycleOwner(), produkList -> {
            if (produkList != null) {
                produkAdapter.setProdukList(produkList);
            }
        });

        FloatingActionButton fabAdd = rootView.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> showAddProdukDialog());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
                if (dy > 0) {
                    bottomNav.animate().translationY(bottomNav.getHeight()).setDuration(200);
                } else if (dy < 0) {
                    bottomNav.animate().translationY(0).setDuration(200);
                }
            }
        });

        return rootView;
    }

    private void showAddProdukDialog() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.tambah_produk, null);
        EditText etNama = dialogView.findViewById(R.id.etNamaProduk);
        EditText etHarga = dialogView.findViewById(R.id.etHargaProduk);
        ImageView ivGambar = dialogView.findViewById(R.id.ivPreviewGambar);
        Button btnPilihGambar = dialogView.findViewById(R.id.btnPilihGambar);
        Button btnSimpan = dialogView.findViewById(R.id.btnSimpanProduk);

        btnPilihGambar.setOnClickListener(v -> {
            selectedImageView = ivGambar;
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .create();

        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String hargaStr = etHarga.getText().toString();
            String imageUriStr = (selectedImageView != null && selectedImageView.getTag() != null)
                    ? selectedImageView.getTag().toString()
                    : "";

            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(hargaStr)) {
                double harga = Double.parseDouble(hargaStr);
                Produk produk = new Produk(0, nama, harga, imageUriStr);
                produkViewModel.tambahProduk(produk);
                dialog.dismiss();

                BottomNavigationView nav = requireActivity().findViewById(R.id.bottom_navigation);
                nav.animate().translationY(nav.getHeight()).setDuration(200);

            } else {
                Toast.makeText(getContext(), "Isi nama dan harga terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null && selectedImageView != null) {
                selectedImageView.setImageURI(uri);
                selectedImageView.setTag(uri.toString());
            }
        }
    }
}

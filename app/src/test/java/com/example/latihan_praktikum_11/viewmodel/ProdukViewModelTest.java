package com.example.latihan_praktikum_11.viewmodel;

import static org.mockito.Mockito.verify;

import com.example.latihan_praktikum_11.data.entity.Produk;
import com.example.latihan_praktikum_11.data.repository.ProdukRepository;
import com.example.latihan_praktikum_11.presentation.viewmodel.ProdukViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProdukViewModelTest {
    private ProdukViewModel viewModel;
    @Mock
    private ProdukRepository mockRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new ProdukViewModel(mockRepository);
    }

    @Test
    public void testTambahProduk() {
        Produk produk = new Produk(0, "Test Produk", 5000, "");
        viewModel.tambahProduk(produk);
        verify(mockRepository).tambahProduk(produk);
    }
}

package com.example.latihan_praktikum_11.presentation.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum_11.R;

public class TentangFragment extends Fragment {

    public TentangFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = rootView.findViewById(R.id.text_home);
        textView.setText("Ini adalah Menu Tentang");
        return rootView;
    }
}


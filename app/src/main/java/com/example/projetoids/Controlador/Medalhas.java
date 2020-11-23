package com.example.projetoids.Controlador;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoids.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Medalhas extends Fragment {


    public Medalhas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medalhas, container, false);
    }

}

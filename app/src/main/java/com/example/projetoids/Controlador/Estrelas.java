package com.example.projetoids.Controlador;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoids.EstrelaItem;
import com.example.projetoids.EstrelasAdapter;
import com.example.projetoids.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Estrelas extends Fragment {

    private RecyclerView estrelaView;
    private RecyclerView.Adapter eAdapter;

    public Estrelas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estrelas, container, false);
        /*
        ArrayList<EstrelaItem> EstrelasList = new ArrayList<>();
        EstrelasList.add(new EstrelaItem(R.drawable.medalha, "1° Medalha","Primeira doação!"));
        EstrelasList.add(new EstrelaItem(R.drawable.ic_trofeu, "Troféu Super Doador","Você atingiu 3 doações!"));
        EstrelasList.add(new EstrelaItem(R.drawable.ic_maos, "Amigo para todas as horas ;)","Acompanhar alguém para dar aquela forcinha"));
        EstrelasList.add(new EstrelaItem(R.drawable.ic_share, "Compartilhar nas redes sociais","Postagem para incentivar amigos :D"));

        estrelaView = findViewById(R.id.estrelasV);
        estrelaView.setHasFixedSize(true);
        eAdapter= new EstrelasAdapter(EstrelasList);

        estrelaView.setLayoutManager(new LinearLayoutManager(getContext()));
        estrelaView.setAdapter(eAdapter);*/
    }

}

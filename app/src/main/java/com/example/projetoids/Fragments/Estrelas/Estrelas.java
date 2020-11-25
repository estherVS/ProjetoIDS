package com.example.projetoids.Fragments.Estrelas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoids.R;

import java.util.List;

public class Estrelas extends Fragment {
    View v;
    private RecyclerView eRecV;
    private List<EstrelaItem> estrelasList;

    public Estrelas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_estrelas, container, false);
        eRecV= v.findViewById(R.id.estrelasV);
        EstrelasAdapter eAdapter = new EstrelasAdapter(estrelasList);
        eRecV.setLayoutManager(new LinearLayoutManager(getActivity()));
        eRecV.setAdapter(eAdapter);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        estrelasList.add(new EstrelaItem(R.drawable.medalha, "1° Medalha", "Primeira doação!"));
        estrelasList.add(new EstrelaItem(R.drawable.ic_trofeu, "Troféu Super Doador", "Você atingiu 3 doações!"));
        estrelasList.add(new EstrelaItem(R.drawable.ic_maos, "Amigo para todas as horas ;)", "Acompanhar alguém para dar aquela forcinha"));
        estrelasList.add(new EstrelaItem(R.drawable.ic_share, "Compartilhar nas redes sociais", "Postagem para incentivar amigos :D"));


    }
}
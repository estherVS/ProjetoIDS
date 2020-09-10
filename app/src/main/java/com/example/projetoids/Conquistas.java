package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Conquistas extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conquistas);

        ArrayList<ConquistaItem> ConquistasList = new ArrayList<>();
        ConquistasList.add(new ConquistaItem(R.drawable.medalha, "1° Medalha","Primeira doação!"));
        ConquistasList.add(new ConquistaItem(R.drawable.ic_trofeu, "Troféu Super Doador","Você atingiu 3 doações!"));
        ConquistasList.add(new ConquistaItem(R.drawable.ic_maos, "Amigo para todas as horas ;)","Acompanhar alguém para dar aquela forcinha"));
        ConquistasList.add(new ConquistaItem(R.drawable.ic_share, "Compartilhar nas redes sociais","Postagem para incentivar amigos :D"));

        mRecyclerView = findViewById(R.id.ConquistasView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter= new ConquistasAdapter(ConquistasList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}

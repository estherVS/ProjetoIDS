package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.projetoids.Classes.Campanhas;
import com.example.projetoids.Classes.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class CampanhasActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference reference;

    private ArrayList<Campanhas> listCampanhas;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // firebase
        reference= FirebaseDatabase.getInstance().getReference();

        //arraylist
        listCampanhas = new ArrayList<>();

        //limpar array list
        ClearAll();

        //metodo get data
        GetDataFromFirebase();

    }

    private void GetDataFromFirebase(){
        Query query = reference.child("Campanhas");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Campanhas campanhas = new Campanhas();

                    campanhas.setImagemUrl(snapshot.child("imagem").getValue().toString());
                    campanhas.setNome(snapshot.child("nome").getValue().toString());

                    listCampanhas.add(campanhas);

                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), listCampanhas);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(listCampanhas!= null){
            listCampanhas.clear();

            if(recyclerAdapter !=null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
         listCampanhas = new ArrayList<>();
    }
}

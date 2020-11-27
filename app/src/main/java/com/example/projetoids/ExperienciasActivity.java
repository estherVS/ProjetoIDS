package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoids.Classes.PostExp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExperienciasActivity extends AppCompatActivity {

    FloatingActionButton addPost;
    RecyclerView expRecycler;
    private DatabaseReference expRef;
    private Context eContext;

    private ArrayList<PostExp> postExpList;
    private ExpAdapter expAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencias);

        setTitle("Experiências");

        addPost=findViewById(R.id.btn_newPost);
        expRecycler = findViewById(R.id.expRecycler);
        expRecycler.setHasFixedSize(true);
        expRecycler.setLayoutManager(new LinearLayoutManager(this));

        expRef = FirebaseDatabase.getInstance().getReference();

        postExpList = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewPostActivity.class);
                startActivity(intent);
            }
        });

    }

    private void GetDataFromFirebase() {
        Query query = expRef.child("Postagens");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    PostExp postExp = new PostExp();


                    postExp.setFoto_usuario(snapshot1.child("foto_usuario").getValue().toString());
                    postExp.setN_usuario(snapshot1.child("n_usuario").getValue().toString());
                    postExp.setCont_texto(snapshot1.child("cont_texto").getValue().toString());
                    postExp.setCont_foto(snapshot1.child("cont_foto").getValue().toString());

                    postExpList.add(postExp);

                }

                expAdapter=new ExpAdapter(getApplicationContext(),postExpList);
                expRecycler.setAdapter(expAdapter);
                expAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll(){
        if(postExpList!=null){
            postExpList.clear();

            if(expAdapter!=null){
                expAdapter.notifyDataSetChanged();
            }
        }

        postExpList = new ArrayList<>();
    }


}

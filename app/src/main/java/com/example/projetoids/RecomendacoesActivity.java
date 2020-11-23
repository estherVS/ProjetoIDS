package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecomendacoesActivity extends AppCompatActivity {

    RecyclerView recRecyclerview;
    RecomendacoesActivity.RecAdapter adapter;
    List<RecGetSet> listData;
    FirebaseDatabase FDB;
    DatabaseReference DBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacoes);

        recRecyclerview = (RecyclerView)findViewById(R.id.recView);

        recRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager LM= new LinearLayoutManager(getApplicationContext());
        recRecyclerview.setLayoutManager(LM);
        recRecyclerview.setItemAnimator(new DefaultItemAnimator());
        recRecyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();

        adapter = new RecomendacoesActivity.RecAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        GetRecomendacoesFirebase();
    }

    private void GetRecomendacoesFirebase() {
        DBR = FDB.getReference("recycler-rec");
        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                RecGetSet data = dataSnapshot.getValue(RecGetSet.class);
                //adding to ArrayList
                listData.add(data);
                //adding List into Adapter/Recyclerview

                recRecyclerview.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class RecAdapter extends RecyclerView.Adapter<RecomendacoesActivity.RecAdapter.RecViewHolder>{

        List<RecGetSet> listArray;

        public RecAdapter(List<RecGetSet> List){
            this.listArray = List;
        }

        @Override
        public RecomendacoesActivity.RecAdapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
            return new RecomendacoesActivity.RecAdapter.RecViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecomendacoesActivity.RecAdapter.RecViewHolder holder, int position) {
            RecGetSet data = listArray.get(position);
            holder.MyText.setText(data.getrec());
        }

        public class RecViewHolder extends RecyclerView.ViewHolder{

            TextView MyText;

            public RecViewHolder(@NonNull View itemView) {
                super(itemView);
                MyText = (TextView)itemView.findViewById(R.id.txtInfo);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }
}

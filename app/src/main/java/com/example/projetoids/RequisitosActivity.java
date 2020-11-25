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

import com.example.projetoids.Classes.ReqGetSet;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RequisitosActivity extends AppCompatActivity {

    RecyclerView reqRecyclerview;
    ReqAdapter adapter;
    List<ReqGetSet> listData;
    FirebaseDatabase FDB;
    DatabaseReference DBR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitos);

        reqRecyclerview = (RecyclerView)findViewById(R.id.requisitosView);

        reqRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager LM= new LinearLayoutManager(getApplicationContext());
        reqRecyclerview.setLayoutManager(LM);
        reqRecyclerview.setItemAnimator(new DefaultItemAnimator());
        reqRecyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();

        adapter = new ReqAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        GetReqDataFirebase();

    }

    void GetReqDataFirebase(){
        DBR = FDB.getReference("recycler-req");
        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ReqGetSet data = dataSnapshot.getValue(ReqGetSet.class);
                //adding to ArrayList
                listData.add(data);
                //adding List into Adapter/Recyclerview

                reqRecyclerview.setAdapter(adapter);

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


    public class ReqAdapter extends RecyclerView.Adapter<ReqAdapter.ReqViewHolder>{

        List<ReqGetSet> listArray;

        public ReqAdapter(List<ReqGetSet> List){
            this.listArray = List;
        }

        @Override
        public ReqAdapter.ReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
            return new ReqViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ReqViewHolder holder, int position) {
            ReqGetSet data = listArray.get(position);
            holder.MyText.setText(data.gettreq());
        }

        public class ReqViewHolder extends RecyclerView.ViewHolder{

            TextView MyText;

            public ReqViewHolder(@NonNull View itemView) {
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


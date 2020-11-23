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

public class ImpTempActivity extends AppCompatActivity {

    RecyclerView imptRecyclerview;
    ImpTempActivity.ImpTempAdapter adapter;
    List<ImpTempGetSet> listData;
    FirebaseDatabase FDB;
    DatabaseReference DBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_temp);

        imptRecyclerview = (RecyclerView)findViewById(R.id.impTempView);

        imptRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager LM= new LinearLayoutManager(getApplicationContext());
        imptRecyclerview.setLayoutManager(LM);
        imptRecyclerview.setItemAnimator(new DefaultItemAnimator());
        imptRecyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();

        adapter = new ImpTempActivity.ImpTempAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        GetImpTempDataFirebase();

    }

    private void GetImpTempDataFirebase() {
        DBR = FDB.getReference("recycler-impt");
        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ImpTempGetSet data = dataSnapshot.getValue(ImpTempGetSet.class);
                //adding to ArrayList
                listData.add(data);
                //adding List into Adapter/Recyclerview

                imptRecyclerview.setAdapter(adapter);

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

    public class ImpTempAdapter extends RecyclerView.Adapter<ImpTempActivity.ImpTempAdapter.ImpTViewHolder>{

        List<ImpTempGetSet> listArray;

        public ImpTempAdapter(List<ImpTempGetSet> List){
            this.listArray = List;
        }

        @Override
        public ImpTempActivity.ImpTempAdapter.ImpTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
            return new ImpTempActivity.ImpTempAdapter.ImpTViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImpTempActivity.ImpTempAdapter.ImpTViewHolder holder, int position) {
            ImpTempGetSet data = listArray.get(position);
            holder.MyText.setText(data.gettexto());
        }

        public class ImpTViewHolder extends RecyclerView.ViewHolder{

            TextView MyText;

            public ImpTViewHolder(@NonNull View itemView) {
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

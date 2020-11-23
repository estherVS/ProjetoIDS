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

public class ImpDefActivity extends AppCompatActivity {

    RecyclerView impdRecyclerview;
    ImpDefActivity.ImpDefAdapter adapter;
    List<ImpDefGetSet> listData;
    FirebaseDatabase FDB;
    DatabaseReference DBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_def);

        impdRecyclerview = (RecyclerView)findViewById(R.id.impDefView);

        impdRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager LM= new LinearLayoutManager(getApplicationContext());
        impdRecyclerview.setLayoutManager(LM);
        impdRecyclerview.setItemAnimator(new DefaultItemAnimator());
        impdRecyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();

        adapter = new ImpDefActivity.ImpDefAdapter(listData);

        FDB = FirebaseDatabase.getInstance();
        GetImpDefDataFirebase();
    }

    private void GetImpDefDataFirebase() {
        DBR = FDB.getReference("recycler-impd");
        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ImpDefGetSet data = dataSnapshot.getValue(ImpDefGetSet.class);
                //adding to ArrayList
                listData.add(data);
                //adding List into Adapter/Recyclerview

                impdRecyclerview.setAdapter(adapter);

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

    public class ImpDefAdapter extends RecyclerView.Adapter<ImpDefActivity.ImpDefAdapter.ImpDViewHolder>{

        List<ImpDefGetSet> listArray;

        public ImpDefAdapter(List<ImpDefGetSet> List){
            this.listArray = List;
        }

        @Override
        public ImpDefActivity.ImpDefAdapter.ImpDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
            return new ImpDefActivity.ImpDefAdapter.ImpDViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImpDefActivity.ImpDefAdapter.ImpDViewHolder holder, int position) {
            ImpDefGetSet data = listArray.get(position);
            holder.MyText.setText(data.gettid());
        }

        public class ImpDViewHolder extends RecyclerView.ViewHolder{

            TextView MyText;

            public ImpDViewHolder(@NonNull View itemView) {
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

package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PerfilActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    TextView nome,dt_nasc,p_email;
    ImageView img_perfil;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setTitle("Perfil");
        progressDialog = new ProgressDialog(PerfilActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        img_perfil= findViewById(R.id.img_photop);
        nome= findViewById(R.id.tv_nome_user);
        dt_nasc=findViewById(R.id.p_nasc);
        p_email=findViewById(R.id.p_email);

        user= FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");

        Query query = databaseReference.orderByChild("email").equalTo((user.getEmail()));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){

                        String url=ds.child("profileUrl").getValue().toString();
                        Picasso.get().load(url).into(img_perfil);
                        nome.setText(ds.child("name").getValue(String.class));
                        dt_nasc.setText(ds.child("birthDate").getValue(String.class));
                        p_email.setText(user.getEmail());

                } progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

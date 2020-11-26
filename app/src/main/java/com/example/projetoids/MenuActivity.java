package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity{

    private CardView infoCard,conqCard,perfilCard,locaisCard,campCard,expCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        infoCard = (CardView)findViewById(R.id.card_info);
        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoActivity();
            }
        });

        conqCard = (CardView)findViewById(R.id.card_conq);
        conqCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConquistasActivity();
            }
        });

        perfilCard = (CardView)findViewById(R.id.card_perfil);
        perfilCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPerfilActivity();
            }
        });

        locaisCard = (CardView)findViewById(R.id.card_locais);
        locaisCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocaisActivity();
            }
        });

        campCard = (CardView)findViewById(R.id.card_camp);
        campCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCampanhasActivity();
            }
        });

        expCard = (CardView)findViewById(R.id.card_exp);
        expCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExperienciasActivity();
            }
        });

    }

    private void openInfoActivity() {
        Intent intent = new Intent(this,InformacoesActivity.class);
        startActivity(intent);
    }

    private void openConquistasActivity() {
        Intent intent = new Intent(this,Conquistas.class);
        startActivity(intent);
    }

    private void openPerfilActivity() {
        Intent intent = new Intent(this,PerfilActivity.class);
        startActivity(intent);
    }

    private void openLocaisActivity() {
        Intent intent = new Intent(this,LocalActivity.class);
        startActivity(intent);
    }

    private void openCampanhasActivity() {
        Intent i= new Intent (this, CampanhasActivity.class);
        startActivity(i);
    }

    private void openExperienciasActivity() {
        Intent intent = new Intent(this,ExperienciasActivity.class);
        startActivity(intent);
    }
}

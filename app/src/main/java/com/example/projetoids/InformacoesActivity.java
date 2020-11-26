package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformacoesActivity extends AppCompatActivity {

    private Button bt1,bt2,bt3,bt4,bt5,bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);



        bt1 = findViewById(R.id.btInfo1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMotivosActivity();
            }
        });

        bt2 = findViewById(R.id.btInfo2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRequisitosActivity();
            }
        });

        bt3 = findViewById(R.id.btInfo3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImpTempActivity();
            }
        });

        bt4 = findViewById(R.id.btInfo4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImpDefActivity();
            }
        });

        bt5 = findViewById(R.id.btInfo5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecomActivity();
            }
        });

        bt6 = findViewById(R.id.btInfo6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntervaloActivity();
            }
        });
    }


    private void openRequisitosActivity() {
        Intent intent = new Intent(this,RequisitosActivity.class);
        startActivity(intent);
    }

    private void openMotivosActivity() {
        Intent intent = new Intent(this,MotivosActivity.class);
        startActivity(intent);
    }

    private void openImpTempActivity() {
        Intent intent = new Intent(this,ImpTempActivity.class);
        startActivity(intent);
    }

    private void openImpDefActivity() {
        Intent intent = new Intent(this,ImpDefActivity.class);
        startActivity(intent);
    }

    private void openRecomActivity() {
        Intent intent = new Intent(this,RecomendacoesActivity.class);
        startActivity(intent);
    }

    private void openIntervaloActivity() {
        Intent intent = new Intent(this,IntervaloActivity.class);
        startActivity(intent);
    }

}

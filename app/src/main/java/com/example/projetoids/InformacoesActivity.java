package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformacoesActivity extends AppCompatActivity {

    private Button bt1,bt2,bt3;

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
    }

    private void openRequisitosActivity() {
        Intent intent = new Intent(this,RequisitosActivity.class);
        startActivity(intent);
    }

    private void openMotivosActivity() {
        Intent intent = new Intent(this,MotivosActivity.class);
        startActivity(intent);
    }
}

package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LocalActivity extends AppCompatActivity {

    private TextView local1;
    private TextView local2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        setTitle("Locais");
        local1 = findViewById(R.id.tv_endereco);

        local1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapLocation1();
            }
        });

        local2 = findViewById(R.id.tv_endereco2);

        local2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapLocation2();
            }
        });
    }

    private void mapLocation1() {
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    private void mapLocation2() {
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }
}

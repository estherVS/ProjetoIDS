package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondScreen extends AppCompatActivity {
    private Button login;
    private Button cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        login = (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        cadastro=(Button)findViewById(R.id.btn_cadastrar);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(SecondScreen.this,MainActivity.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(SecondScreen.this, Cadastro.class);
        startActivity(intent);
    }

}

package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private FirebaseAuth mAuth;
    TextView esq_senha;
    EditText ev_user;
    EditText ev_password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        esq_senha = findViewById(R.id.esq_senha);
        ev_user = findViewById(R.id.ev_user);
        ev_password = findViewById(R.id.ev_password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {

        String msg = "Iniciando Login";
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

        String email = ev_user.getText().toString();
        String password = ev_password.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startMenuActivity(user);
                        } else {
                            Toast.makeText(MainActivity.this, "Falha ao realizar o login.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            startMenuActivity(currentUser);
        } else {
            String msg = "Erro ao autenticar usuário";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    private void startMenuActivity(FirebaseUser currentUser) {
        String msg = "Iniciando Tela principal do app";
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }




}


/*/
    public void onClick(View v) {
                openActivity2();
            }
    public void openActivity2(){
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}/*/
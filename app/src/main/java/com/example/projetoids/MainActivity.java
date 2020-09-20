package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


    EditText ev_user;
    EditText ev_password;
    Button login;
    TextView tvRegistrar;
    TextView esq_senha;
    String TAG = "MainActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        tvRegistrar = findViewById(R.id.tvRegistrar);
        esq_senha = findViewById(R.id.esq_senha);
        ev_user = findViewById(R.id.ev_user);
        ev_password = findViewById(R.id.ev_password);
        login = findViewById(R.id.login);
        setListeners();
    }

        @Override
        public void onStart() {
            super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            startMenuActivity(currentUser);
        }


    private void doLogin(String email, String password) {
        Toast.makeText(MainActivity.this, "Iniciando Login.",
                Toast.LENGTH_SHORT).show();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startMenuActivity(user);
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            startMenuActivity(null);
                        }
                    }
                });

    }


    private void startMenuActivity(FirebaseUser firebaseUser){
        if(firebaseUser == null){
            Toast.makeText(MainActivity.this, "Erro.",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(MainActivity.this, "Iniciando Tela principal do app.",Toast.LENGTH_LONG).show();

    }

  /*private void recoverPassword(String email){
        if(email == null){
            Toast.makeText(MainActivity.this, "Adicione o email.",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Adicione o email.",Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Erro ao enviar email de redefinição de senha.",Toast.LENGTH_LONG).show();

                }

            }
        });
    }*/

    private void setListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ev_user.getText().toString();
                String password = ev_password.getText().toString();
                doLogin(email, password);

            }
        });
        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cadastro.class));
            }
        });

}

}


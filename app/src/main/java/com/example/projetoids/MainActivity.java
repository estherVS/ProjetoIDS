package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

    ProgressDialog progressDialog;
    EditText ev_user;
    EditText ev_password;
    Button login;
    TextView tvRegistrar;
    TextView esq_senha;
    String TAG = "MainActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        tvRegistrar = findViewById(R.id.tvRegistrar);
        esq_senha = findViewById(R.id.esq_senha);
        ev_user = findViewById(R.id.ev_user);
        ev_password = findViewById(R.id.ev_password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ev_user.getText().toString().trim();
                String password = ev_password.getText().toString().trim();
                setTitle("Menu Principal");
                if(email.isEmpty()){
                    ev_user.setError("Por favor, preencha o campo");
                    ev_user.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    ev_user.setError("Por favor, forneça um email válido");
                    ev_user.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    ev_password.setError("Por favor, preencha o campo");
                    ev_password.requestFocus();
                    return;
                }
                if(password.length()<6){
                    ev_password.setError("A senha deve conter no mínino 6 caracteres");
                    ev_password.requestFocus();
                    return;

                }
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:success");
                                    progressDialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));


                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Tente novamente",
                                            Toast.LENGTH_SHORT).show();


                                }
                            }
                        });


            }
        });

        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cadastro.class));
            }
        });
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


}


package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Cadastro extends AppCompatActivity {

    private EditText evName, evData, evMail, evSenha;
    private Button btRegistrar;
    private FirebaseAuth mAuth;
    /*private ProgressBar progressBar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        evData = findViewById(R.id.evData);
        evName = findViewById(R.id.evName);
        evMail = findViewById(R.id.evMail);
        evSenha = findViewById(R.id.evSenha);
        btRegistrar = findViewById(R.id.btRegistrar);
        //progressBar= findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = evName.getText().toString().trim();
                String birthDate = evData.getText().toString().trim();
                String email = evMail.getText().toString().trim();
                String password = evSenha.getText().toString().trim();

                if(name.isEmpty()){
                    evName.setError("Por favor, preencha o campo");
                    evName.requestFocus();
                    return;
                }
                if(birthDate.isEmpty()){
                    evData.setError("Por favor, preencha o campo");
                    evData.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    evMail.setError("Por favor, preencha o campo");
                    evMail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    evMail.setError("Por favor, forneça um email válido");
                    evMail.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    evSenha.setError("Por favor, preencha o campo");
                    evSenha.requestFocus();
                    return;
                }
                if(password.length()<6){
                    evSenha.setError("A senha deve conter no mínino 6 caracteres");
                    evSenha.requestFocus();
                    return;

                }
                //

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    String name = evName.getText().toString().trim();
                                    String birthDate = evData.getText().toString().trim();
                                    String email = evMail.getText().toString().trim();

                                    User user = new User(name,email,birthDate);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                                //progressbar.setVisibility(view.GONE);
                                            }else{
                                                Toast.makeText(Cadastro.this, "Falha ao cadastrar usuário1!", Toast.LENGTH_LONG).show();
                                                //progressbar.setVisibility(view.GONE);
                                            }
                                        }
                                    });

                                }else{
                                    Toast.makeText(Cadastro.this, "Falha ao cadastrar usuário!", Toast.LENGTH_LONG).show();
                                    //progressbar.setVisibility(view.GONE);
                                }
                            }
                        });
            }
        });
    }

    private DatePickerDialog getDatePickerDialogForToday(DatePickerDialog.OnDateSetListener onDateSetListener){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =  new DatePickerDialog(Cadastro.this, onDateSetListener, year, month, day);
        return datePickerDialog;
    }

    private String getDateFormatted(int day, int month, int year){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);

        Date date = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateFormat.format(date);

        return dateStr;
    }


    private void finishRegister(FirebaseUser firebaseUser){
        if(firebaseUser == null){
            Toast.makeText(Cadastro.this, "Erro ao criar o usuário.", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(Cadastro.this, "Usuário cadastrado.", Toast.LENGTH_SHORT).show();
        finish();

    }
}

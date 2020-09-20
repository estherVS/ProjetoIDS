package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Cadastro extends AppCompatActivity {

    private TextView evName;
    private EditText evData;
    private EditText evMail;
    private EditText evSenha;
    private Button btRegistrar;
    private FirebaseAuth mAuth;
    private String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        evData = findViewById(R.id.evData);
        evName = findViewById(R.id.evName);
        evMail = findViewById(R.id.evMail);
        evSenha = findViewById(R.id.evSenha);
        btRegistrar = findViewById(R.id.btRegistrar);

        mAuth = FirebaseAuth.getInstance();



        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = evName.getText().toString();
                String birthDate = evData.getText().toString();
                String email = evMail.getText().toString();
                String password = evSenha.getText().toString();

                registerNewUser(name, birthDate, email, password);
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

    private void registerNewUser(String name, String date, String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finishRegister(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Cadastro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            finishRegister(null);
                        }
                    }
                });

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

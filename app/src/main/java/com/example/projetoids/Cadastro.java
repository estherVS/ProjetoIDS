package com.example.projetoids;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetoids.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Cadastro extends AppCompatActivity {

    private EditText evName, evData, evMail, evSenha;
    private Button btRegistrar;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private ImageButton mBtnSelectPhoto;
    private Uri mSelectedUri;
    private ImageView mImgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        evData = findViewById(R.id.evData);
        evName = findViewById(R.id.evName);
        evMail = findViewById(R.id.evMail);
        evSenha = findViewById(R.id.evSenha);
        btRegistrar = findViewById(R.id.btRegistrar);
        mBtnSelectPhoto = findViewById(R.id.btn_select_photo);
        mImgPhoto = findViewById(R.id.img_photo);

        mBtnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

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
                progressDialog = new ProgressDialog(Cadastro.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    String filename = UUID.randomUUID().toString();
                                    final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/" + filename);
                                    ref.putFile(mSelectedUri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            Log.i("Teste",uri.toString());

                                                            String profileUrl = uri.toString();
                                                            String name = evName.getText().toString().trim();
                                                            String birthDate = evData.getText().toString().trim();
                                                            String email = evMail.getText().toString().trim();

                                                            User user = new User(profileUrl,name,email,birthDate);

                                                            FirebaseDatabase.getInstance().getReference("Users")
                                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                    if(task.isSuccessful()){
                                                                        Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                                                        progressDialog.dismiss();

                                                                        Intent intent=new Intent(Cadastro.this,MenuActivity.class);
                                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                        startActivity(intent);
                                                                    }else{
                                                                        Toast.makeText(Cadastro.this, "Falha ao cadastrar usuário1!", Toast.LENGTH_LONG).show();
                                                                        progressDialog.dismiss();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });



                                }else{
                                    Toast.makeText(Cadastro.this, "Falha ao cadastrar usuário!", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0){
            mSelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                mImgPhoto.setImageDrawable(new BitmapDrawable(bitmap));
            } catch (IOException e) {

            }
        }
    }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
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

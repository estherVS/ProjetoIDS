package com.example.projetoids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class FirstScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                verifyAuthentication();
            }
        },SPLASH_TIME_OUT);
    }
    private void verifyAuthentication() {
        if(FirebaseAuth.getInstance().getCurrentUser()!= null){
            Intent next = new Intent(FirstScreen.this, SecondScreen.class);
            next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(next);
        }else{
            Intent next = new Intent(FirstScreen.this, MenuActivity.class);
            next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(next);
        }
    }
}

package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class PrincipalActivity extends Activity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 3000; // 3 segundos
    MediaPlayer sonido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        sonido = MediaPlayer.create(this, R.raw.roblox);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                sonido.start();
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(getApplicationContext(), Camareros_Activity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
package com.example.saluddiaria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ajustes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajustes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void ActualizarDatos(View v){
        Intent i = new Intent(this, ActualizarDatos.class);
        startActivity(i);
    }

    public void AjustarHora(View v){
        Intent i = new Intent(this, horaEncuesta.class);
        startActivity(i);
    }

    public void AjustarNotificaciones(View v){
        Intent i = new Intent(this, Notificaciones.class);
        startActivity(i);
    }

    public void logOut(View v){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = datos.edit();
        editor.remove("correo");
        editor.apply();
        Toast.makeText(this, "Sesion cerrada con exito", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
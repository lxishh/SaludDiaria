package com.example.saluddiaria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class horaEncuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Verificar si la actividad ya fue mostrada
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean horaEncuestaMostrada = prefs.getBoolean("horaEncuestaMostrada", false);

        if (horaEncuestaMostrada) {
            // Si ya fue mostrada, ir directamente a la actividad Encuesta
            Intent i = new Intent(this, Encuesta.class);
            startActivity(i);
            finish(); // Cerrar la actividad actual
            return; // Salir del método onCreate
        }

        setContentView(R.layout.activity_hora_encuesta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimePicker reloj = findViewById(R.id.HoraReloj);
        reloj.setIs24HourView(true);
    }

    public void definirHora(View v) {
        // Recuperar el timepicker
        TimePicker SeleccionarTiempo = findViewById(R.id.HoraReloj);

        // Obtener hora y minutos
        int hora = SeleccionarTiempo.getHour();
        int minutos = SeleccionarTiempo.getMinute();

        // Formatear
        String tiempoSeleccionado = String.format("%02d:%02d", hora, minutos);

        // Mostrar hora seleccionada
        Toast.makeText(this, "Hora seleccionada: " + tiempoSeleccionado, Toast.LENGTH_SHORT).show();

        // Guardar que la actividad ha sido mostrada
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("horaEncuestaMostrada", true); // Cambia a true
        editor.apply();

        // Iniciar la siguiente actividad
        Intent i = new Intent(this, Encuesta.class);
        startActivity(i);
    }
}

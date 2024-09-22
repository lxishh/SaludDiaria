package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
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
        setContentView(R.layout.activity_hora_encuesta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimePicker reloj = findViewById(R.id.HoraReloj);
        reloj.setIs24HourView(true);
    }


    public void definirHora(View v){
        //Recuperar el timepicker
        TimePicker SeleccionarTiempo = findViewById(R.id.HoraReloj);

        //Obtener hora y minutos
        int hora = SeleccionarTiempo.getHour();
        int minutos = SeleccionarTiempo.getMinute();

        //Formatear
        String tiempoSeleccionado = String.format("%02d:%02d", hora, minutos);

        //Mostrar hora seleccionada
        Toast.makeText(this, "Hora seleccionada: " + tiempoSeleccionado, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Encuesta.class);
        startActivity(i);
    }
}
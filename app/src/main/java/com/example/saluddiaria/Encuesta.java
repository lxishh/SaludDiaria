package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Encuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encuesta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void EnviarForm(View v){
        //Recuperar view
        Spinner respuesta1 = (Spinner) findViewById(R.id.respuesta1);
        Spinner respuesta2 = (Spinner) findViewById(R.id.respuesta2);
        Spinner respuesta3 = (Spinner) findViewById(R.id.respuesta3);
        Spinner respuesta4 = (Spinner) findViewById(R.id.respuesta4);
        Spinner respuesta5 = (Spinner) findViewById(R.id.respuesta5);
        //Recuperar valor
        String resp1 = respuesta1.getSelectedItem().toString();
        String resp2 = respuesta2.getSelectedItem().toString();
        String resp3 = respuesta3.getSelectedItem().toString();
        String resp4 = respuesta4.getSelectedItem().toString();
        String resp5 = respuesta5.getSelectedItem().toString();

        if (!resp1.isEmpty() && !resp2.isEmpty() && !resp3.isEmpty() && !resp4.isEmpty() && !resp5.isEmpty()) {
            // Procesar las respuestas
            // Por ejemplo, puedes guardarlas en una base de datos o enviarlas a otra actividad
            Toast.makeText(this, "Encuesta enviada con éxito", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Principal.class);
            startActivity(i);
        } else {
            // Mostrar mensaje de error si no se han respondido todas las preguntas
            Toast.makeText(this, "Por favor responde todas las preguntas", Toast.LENGTH_SHORT).show();
        }
    }
}
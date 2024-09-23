package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CitaMedica extends AppCompatActivity {
    private EditText etLugar, etNombreDoc, etEspecialidad, etFechaCita, etHoraCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citamedica);

        // Inicializar los EditTexts
        etLugar = findViewById(R.id.etLugar);
        etNombreDoc = findViewById(R.id.etNombreDoc);
        etEspecialidad = findViewById(R.id.etEspecialidad);
        etFechaCita = findViewById(R.id.etFechaCita);
        etHoraCita = findViewById(R.id.etHoraCita);

        // Configurar el botón para agendar la cita
        Button btnAgendar = findViewById(R.id.button17);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para devolver los datos a la actividad anterior
                Intent resultIntent = new Intent();
                resultIntent.putExtra("lugar", etLugar.getText().toString());
                resultIntent.putExtra("nombreDoc", etNombreDoc.getText().toString());
                resultIntent.putExtra("especialidad", etEspecialidad.getText().toString());
                resultIntent.putExtra("fecha", etFechaCita.getText().toString());
                resultIntent.putExtra("hora", etHoraCita.getText().toString());

                // Establecer el resultado y finalizar la actividad
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}

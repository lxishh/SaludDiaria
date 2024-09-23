package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Agenda extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private ArrayList<Cita> citas;
    private CitaAdapter citaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agenda);

        // Inicializar la lista de citas
        citas = new ArrayList<>();

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        citaAdapter = new CitaAdapter(citas);
        recyclerView.setAdapter(citaAdapter);

        // Configurar el botón para agregar una nueva cita
        Button btnAgregarCita = findViewById(R.id.btnAgregarCita);
        btnAgregarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar CitaMedica para agregar una nueva cita
                Intent intent = new Intent(Agenda.this, CitaMedica.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    // Recibir el resultado de CitaMedica
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Obtener los datos de la cita
            String lugar = data.getStringExtra("lugar");
            String nombreDoc = data.getStringExtra("nombreDoc");
            String especialidad = data.getStringExtra("especialidad");
            String fecha = data.getStringExtra("fecha");
            String hora = data.getStringExtra("hora");

            // Agregar la nueva cita a la lista y notificar al adaptador
            Cita nuevaCita = new Cita(lugar, nombreDoc, especialidad, fecha, hora);
            citas.add(nuevaCita);
            citaAdapter.notifyDataSetChanged();
        }
    }
}

package com.example.saluddiaria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FichaMedica extends AppCompatActivity {

    private EditText nombreEditText, padecimientosEditText, detallesEditText, alergiasEditText, medicamentosEditText;
    private TextView textView;
    private Button editButton, showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ficha_medica);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombreEditText = findViewById(R.id.nombreEditText);
        padecimientosEditText = findViewById(R.id.padecimientosEditText);
        detallesEditText = findViewById(R.id.detallesEditText);
        alergiasEditText = findViewById(R.id.alergiasEditText);
        medicamentosEditText = findViewById(R.id.medicamentosEditText);
        textView = findViewById(R.id.textView);
        showButton = findViewById(R.id.button);
        editButton = findViewById(R.id.editButton);

        // Ocultar todos los EditTexts y el TextView al inicio
        nombreEditText.setVisibility(View.GONE);
        padecimientosEditText.setVisibility(View.GONE);
        detallesEditText.setVisibility(View.GONE);
        alergiasEditText.setVisibility(View.GONE);
        medicamentosEditText.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        editButton.setVisibility(View.GONE);
        showButton.setVisibility(View.GONE);

        // Establecer un texto inicial (opcional)
        String textoInicial = "Nombre: \nPadecimientos: \nDetalles: \nAlergias: \nMedicamentos:";
        textView.setText(textoInicial);

        // Mostrar el TextView y el botón de Editar al inicio
        textView.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.VISIBLE);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rellenar los EditText con el texto actual
                String[] datos = textView.getText().toString().split("\n");
                if (datos.length >= 5) {
                    nombreEditText.setText(datos[0].replace("Nombre: ", ""));
                    padecimientosEditText.setText(datos[1].replace("Padecimientos: ", ""));
                    detallesEditText.setText(datos[2].replace("Detalles: ", ""));
                    alergiasEditText.setText(datos[3].replace("Alergias: ", ""));
                    medicamentosEditText.setText(datos[4].replace("Medicamentos: ", ""));
                }
                // Mostrar los EditTexts y ocultar el TextView
                nombreEditText.setVisibility(View.VISIBLE);
                padecimientosEditText.setVisibility(View.VISIBLE);
                detallesEditText.setVisibility(View.VISIBLE);
                alergiasEditText.setVisibility(View.VISIBLE);
                medicamentosEditText.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                editButton.setVisibility(View.GONE);
                showButton.setVisibility(View.VISIBLE); // Mostrar el botón de Mostrar texto
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreEditText.getText().toString();
                String padecimientos = padecimientosEditText.getText().toString();
                String detalles = detallesEditText.getText().toString();
                String alergias = alergiasEditText.getText().toString();
                String medicamentos = medicamentosEditText.getText().toString();

                String mostrarTexto = "Nombre: " + nombre + "\nPadecimientos: " + padecimientos +
                        "\nDetalles: " + detalles + "\nAlergias: " + alergias +
                        "\nMedicamentos: " + medicamentos;

                textView.setText(mostrarTexto);
                textView.setVisibility(View.VISIBLE); // Mostrar el TextView
                nombreEditText.setVisibility(View.GONE); // Ocultar los EditTexts
                padecimientosEditText.setVisibility(View.GONE);
                detallesEditText.setVisibility(View.GONE);
                alergiasEditText.setVisibility(View.GONE);
                medicamentosEditText.setVisibility(View.GONE);
                editButton.setVisibility(View.VISIBLE); // Mostrar el botón de Editar
                showButton.setVisibility(View.GONE); // Ocultar el botón de Mostrar texto
            }
        });
    }
}

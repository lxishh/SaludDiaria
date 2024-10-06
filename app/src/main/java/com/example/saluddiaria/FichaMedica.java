package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FichaMedica extends AppCompatActivity {

    private EditText nombreEditText, padecimientosEditText, detallesEditText, alergiasEditText, medicamentosEditText;
    private Button button;

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

        // Referencias a los EditText y Button
        nombreEditText = findViewById(R.id.nombreEditText);
        padecimientosEditText = findViewById(R.id.padecimientosEditText);
        detallesEditText = findViewById(R.id.detallesEditText);
        alergiasEditText = findViewById(R.id.alergiasEditText);
        medicamentosEditText = findViewById(R.id.medicamentosEditText);
        button = findViewById(R.id.button);

        // Configurar el OnClickListener para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados
                String nombre = nombreEditText.getText().toString();
                String padecimientos = padecimientosEditText.getText().toString();
                String detalles = detallesEditText.getText().toString();
                String alergias = alergiasEditText.getText().toString();
                String medicamentos = medicamentosEditText.getText().toString();

                // Cambiar los EditText a no editables
                setEditTextNotEditable(nombreEditText);
                setEditTextNotEditable(padecimientosEditText);
                setEditTextNotEditable(detallesEditText);
                setEditTextNotEditable(alergiasEditText);
                setEditTextNotEditable(medicamentosEditText);

                // Deshabilitar el botón después de guardar
                button.setEnabled(false);
            }
        });
    }

    // Método para deshabilitar un EditText
    private void setEditTextNotEditable(EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setClickable(false);
        editText.setBackgroundColor(Color.TRANSPARENT); // Cambiar color de fondo a transparente (opcional)
    }
}

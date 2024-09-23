package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Hidratacion extends AppCompatActivity {

    private ProgressBar progressCircle;
    private EditText etMlTomados;
    private TextView tvLunes, tvMartes, tvMiercoles, tvJueves, tvViernes, tvSabado, tvDomingo;  // Aquí irían los demás días de la semana

    private int totalMlTomados = 0;
    private final int objetivoDiario = 2000;  // Ejemplo: 2000ml como objetivo diario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidratacion);

        progressCircle = findViewById(R.id.progressCircle);
        etMlTomados = findViewById(R.id.etMlTomados);
        tvLunes = findViewById(R.id.tvLunes);
        tvMartes = findViewById(R.id.tvMartes);
        tvMiercoles = findViewById(R.id.tvMiercoles);
        tvJueves = findViewById(R.id.tvJueves);
        tvViernes = findViewById(R.id.tvViernes);
        tvSabado = findViewById(R.id.tvSabado);
        tvDomingo = findViewById(R.id.tvDomingo);

        Button btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(v -> {
            String mlTomadosStr = etMlTomados.getText().toString();
            if (!mlTomadosStr.isEmpty()) {
                int mlTomados = Integer.parseInt(mlTomadosStr);
                totalMlTomados += mlTomados;

                if (totalMlTomados > objetivoDiario) {
                    totalMlTomados = objetivoDiario;
                }

                progressCircle.setProgress(totalMlTomados);

                // Si se cumple el objetivo, marcar el día de la semana
                if (totalMlTomados >= objetivoDiario) {
                    marcarDiaCompletado(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
                }
            }
        });
    }

    private void marcarDiaCompletado(int diaDeLaSemana) {
        // Día 2 es lunes, 3 es martes, etc.
        switch (diaDeLaSemana) {
            case Calendar.MONDAY:
                tvLunes.setTextColor(Color.BLUE);  // Marcar en azul o poner un check
                break;
            case Calendar.TUESDAY:
                tvMartes.setTextColor(Color.BLUE);
                break;
            case Calendar.WEDNESDAY:
                tvMiercoles.setTextColor(Color.BLUE);
                break;
            case Calendar.THURSDAY:
                tvJueves.setTextColor(Color.BLUE);
                break;
            case Calendar.FRIDAY:
                tvViernes.setTextColor(Color.BLUE);
                break;
            case Calendar.SATURDAY:
                tvSabado.setTextColor(Color.BLUE);
                break;
            case Calendar.SUNDAY:
                tvDomingo.setTextColor(Color.BLUE);
                break;
        }
    }
}

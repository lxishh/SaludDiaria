package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class HidratacionFragment extends Fragment {

    private ProgressBar progressCircle;
    private EditText etMlTomados;
    private TextView tvLunes, tvMartes, tvMiercoles, tvJueves, tvViernes, tvSabado, tvDomingo;

    private int totalMlTomados = 0;
    private final int objetivoDiario = 2000;  // Ejemplo: 2000ml como objetivo diario

    public HidratacionFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_hidratacion, container, false);

        // Inicializar las vistas
        progressCircle = view.findViewById(R.id.progressCircle);
        etMlTomados = view.findViewById(R.id.etMlTomados);
        tvLunes = view.findViewById(R.id.tvLunes);
        tvMartes = view.findViewById(R.id.tvMartes);
        tvMiercoles = view.findViewById(R.id.tvMiercoles);
        tvJueves = view.findViewById(R.id.tvJueves);
        tvViernes = view.findViewById(R.id.tvViernes);
        tvSabado = view.findViewById(R.id.tvSabado);
        tvDomingo = view.findViewById(R.id.tvDomingo);

        Button btnActualizar = view.findViewById(R.id.btnActualizar);
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

        return view;
    }

    private void marcarDiaCompletado(int diaDeLaSemana) {
        // Día 2 es lunes, 3 es martes, etc.
        switch (diaDeLaSemana) {
            case Calendar.MONDAY:
                tvLunes.setTextColor(Color.WHITE);
                tvLunes.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.TUESDAY:
                tvMartes.setTextColor(Color.WHITE);
                tvMartes.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.WEDNESDAY:
                tvMiercoles.setTextColor(Color.WHITE);
                tvMiercoles.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.THURSDAY:
                tvJueves.setTextColor(Color.WHITE);
                tvJueves.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.FRIDAY:
                tvViernes.setTextColor(Color.WHITE);
                tvViernes.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.SATURDAY:
                tvSabado.setTextColor(Color.WHITE);
                tvSabado.setBackgroundResource(R.drawable.background_redondo);
                break;
            case Calendar.SUNDAY:
                tvDomingo.setTextColor(Color.WHITE);
                tvDomingo.setBackgroundResource(R.drawable.background_redondo);
                break;
        }
    }
}

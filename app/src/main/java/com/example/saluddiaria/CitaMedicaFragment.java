package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class CitaMedicaFragment extends Fragment {

    private EditText etLugar, etNombreDoc, etEspecialidad, etFechaCita, etHoraCita;

    public CitaMedicaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_cita_medica, container, false);

        // Inicializar los EditTexts
        etLugar = view.findViewById(R.id.etLugar);
        etNombreDoc = view.findViewById(R.id.etNombreDoc);
        etEspecialidad = view.findViewById(R.id.etEspecialidad);
        etFechaCita = view.findViewById(R.id.etFechaCita);
        etHoraCita = view.findViewById(R.id.etHoraCita);

        // Configurar el botón para agendar la cita
        Button btnAgendar = view.findViewById(R.id.button17);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger los datos de la cita médica
                String lugar = etLugar.getText().toString();
                String nombreDoc = etNombreDoc.getText().toString();
                String especialidad = etEspecialidad.getText().toString();
                String fecha = etFechaCita.getText().toString();
                String hora = etHoraCita.getText().toString();

                // Crear un Bundle para pasar los datos a AgendaFragment
                Bundle bundle = new Bundle();
                bundle.putString("lugar", lugar);
                bundle.putString("nombreDoc", nombreDoc);
                bundle.putString("especialidad", especialidad);
                bundle.putString("fecha", fecha);
                bundle.putString("hora", hora);

                // Pasar los datos a AgendaFragment
                getParentFragmentManager().setFragmentResult("citaResult", bundle);

                // Volver al fragmento anterior (AgendaFragment)
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}

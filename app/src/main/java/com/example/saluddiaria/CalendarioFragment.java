package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class CalendarioFragment extends Fragment {

    public CalendarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflando el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        // Configurar el padding en la vista
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el evento para ir a la actividad Agenda

        // Configurando el botón de ver agenda
        view.findViewById(R.id.btnAgenda).setOnClickListener(v -> {
            AgendaFragment a = new AgendaFragment();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, a)
                    .addToBackStack(null)  // Agrega a la pila de retroceso
                    .commit();
        });


        return view;
    }
}

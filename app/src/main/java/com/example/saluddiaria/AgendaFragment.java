package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AgendaFragment extends Fragment {

    private ArrayList<Cita> citas;
    private CitaAdapter citaAdapter;

    public AgendaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        // Inicializar la lista de citas
        citas = new ArrayList<>();

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        citaAdapter = new CitaAdapter(citas);
        recyclerView.setAdapter(citaAdapter);

        // Configurar el botón para agregar una nueva cita
        Button btnAgregarCita = view.findViewById(R.id.btnAgregarCita);
        btnAgregarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reemplazar el fragmento actual con CitaMedicaFragment
                CitaMedicaFragment citaMedicaFragment = new CitaMedicaFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, citaMedicaFragment)
                        .addToBackStack(null)  // Permitir volver atrás
                        .commit();
            }
        });

        // Escuchar el resultado desde CitaMedicaFragment
        getParentFragmentManager().setFragmentResultListener("citaResult", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(String requestKey, Bundle result) {
                // Obtener los datos de la cita
                String lugar = result.getString("lugar");
                String nombreDoc = result.getString("nombreDoc");
                String especialidad = result.getString("especialidad");
                String fecha = result.getString("fecha");
                String hora = result.getString("hora");

                // Crear una nueva cita y añadirla a la lista
                Cita nuevaCita = new Cita(lugar, nombreDoc, especialidad, fecha, hora);
                citas.add(nuevaCita);
                citaAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}

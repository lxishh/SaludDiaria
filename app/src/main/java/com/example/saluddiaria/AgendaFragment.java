package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saluddiaria.adapter.CitaAdapter;
import com.example.saluddiaria.model.Cita;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AgendaFragment extends Fragment {

    private CitaAdapter citaAdapter;
    private FirebaseFirestore db;

    public AgendaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAgendados);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear la consulta para obtener citas
        Query query = db.collection("citas");

        // Configurar FirestoreRecyclerOptions
        FirestoreRecyclerOptions<Cita> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Cita>()
                        .setQuery(query, Cita.class)
                        .build();

        citaAdapter = new CitaAdapter(firestoreRecyclerOptions, getContext(), getParentFragmentManager());
        recyclerView.setAdapter(citaAdapter);

        // Configurar el botón para agregar una nueva cita
        view.findViewById(R.id.btnAgregarCita).setOnClickListener(v -> {
            // Abre un nuevo fragmento para agregar una cita
            CitaMedicaFragment citaMedicaFragment = new CitaMedicaFragment();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, citaMedicaFragment)
                    .addToBackStack(null)
                    .commit();
        });




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        citaAdapter.startListening(); // Comenzar a escuchar cambios en Firestore
    }

    @Override
    public void onStop() {
        super.onStop();
        citaAdapter.stopListening(); // Detener la escucha al salir del fragmento
    }
}

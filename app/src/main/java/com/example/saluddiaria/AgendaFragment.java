package com.example.saluddiaria;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class AgendaFragment extends Fragment {

    private ArrayList<Cita> citas;
    private CitaAdapter citaAdapter;
    private FirebaseFirestore db;

    public AgendaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        // Inicializar la lista de citas y Firestore
        citas = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        citaAdapter = new CitaAdapter(citas);
        recyclerView.setAdapter(citaAdapter);

        // Cargar citas desde Firestore
        cargarCitas();

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

        // Escuchar el resultado desde CitaMedicaFragment (para agregar nuevas citas)
        getParentFragmentManager().setFragmentResultListener("citaResult", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(String requestKey, Bundle result) {
                // Obtener los datos de la cita
                String lugar = result.getString("lugar");
                String nombreDoc = result.getString("nombreDoc");
                String especialidad = result.getString("especialidad");
                String fecha = result.getString("fecha");
                String hora = result.getString("hora");

                // Generar un ID único para la nueva cita
                String id = db.collection("citas").document().getId(); // Genera un ID único
                // Crear una nueva cita
                Cita nuevaCita = new Cita(id, lugar, nombreDoc, especialidad, fecha, hora); // Llama al constructor con id
                // Guardar la nueva cita en Firestore
                agregarCita(nuevaCita);
            }
        });

        // Escuchar el resultado desde EditarCitaFragment (para actualizar o eliminar citas)
        getParentFragmentManager().setFragmentResultListener("editarCitaResult", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(String requestKey, Bundle result) {
                if (result.containsKey("eliminarCita")) {
                    // Eliminar la cita
                    int position = result.getInt("position");
                    eliminarCita(citas.get(position).getId(), position);
                } else {
                    // Actualizar la cita
                    int position = result.getInt("position");
                    Cita citaActualizada = (Cita) result.getSerializable("citaActualizada");
                    actualizarCita(citaActualizada);
                }
            }
        });

        return view;
    }

    private void cargarCitas() {
        db.collection("citas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        citas.clear(); // Limpiar la lista antes de agregar
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Cita cita = document.toObject(Cita.class);
                            cita.setId(document.getId()); // Guarda el ID de Firestore
                            citas.add(cita);
                        }
                        citaAdapter.notifyDataSetChanged(); // Notificar al adaptador sobre los nuevos datos
                    } else {
                        Log.d("Firestore", "Error getting documents: ", task.getException());
                    }
                });
    }

    private void agregarCita(Cita nuevaCita) {
        db.collection("citas")
                .add(nuevaCita)
                .addOnSuccessListener(documentReference -> {
                    nuevaCita.setId(documentReference.getId()); // Guarda el ID de Firestore
                    citas.add(nuevaCita);
                    citaAdapter.notifyItemInserted(citas.size() - 1); // Notificar al adaptador
                })
                .addOnFailureListener(e -> Log.w("Firestore", "Error adding document", e));
    }

    private void actualizarCita(Cita citaActualizada) {
        db.collection("citas").document(citaActualizada.getId())
                .set(citaActualizada) // O usar .update(citaActualizada.toMap()) si prefieres
                .addOnSuccessListener(aVoid -> {
                    int position = citas.indexOf(citaActualizada);
                    if (position != -1) {
                        citas.set(position, citaActualizada);
                        citaAdapter.notifyItemChanged(position); // Notificar al adaptador
                    }
                })
                .addOnFailureListener(e -> Log.w("Firestore", "Error updating document", e));
    }

    private void eliminarCita(String id, int position) {
        db.collection("citas").document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    citas.remove(position);
                    citaAdapter.notifyItemRemoved(position); // Notificar al adaptador
                    citaAdapter.notifyItemRangeChanged(position, citas.size()); // Ajustar el rango
                })
                .addOnFailureListener(e -> Log.w("Firestore", "Error deleting document", e));
    }
}

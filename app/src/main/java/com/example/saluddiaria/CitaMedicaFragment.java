package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CitaMedicaFragment extends Fragment {

    private EditText etLugar, etNombreDoc, etFechaCita, etHoraCita, etEspecialidad;
    private FirebaseFirestore mfirestore;
    private String id_cita;

    public CitaMedicaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mfirestore = FirebaseFirestore.getInstance(); // Inicializar Firebase
        if (getArguments() != null) {
            id_cita = getArguments().getString("id_cita");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_cita_medica, container, false);

        // Inicializar los EditTexts y Spinner
        etLugar = view.findViewById(R.id.etLugarCita);
        etNombreDoc = view.findViewById(R.id.etNombreDoc);
        etEspecialidad = view.findViewById(R.id.etEspecialidadDoc);
        etFechaCita = view.findViewById(R.id.etFechaCita);
        etHoraCita = view.findViewById(R.id.etHoraCita);


        // Configurar el botón para agendar la cita
        Button btnAgendar = view.findViewById(R.id.btnListoMed);
        if (id_cita == null || id_cita.isEmpty()) {
            btnAgendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Recoger los datos de la cita médica
                    String lugar = etLugar.getText().toString().trim();
                    String nombreDoc = etNombreDoc.getText().toString().trim();
                    String especialidad = etEspecialidad.getText().toString().trim();
                    String fecha = etFechaCita.getText().toString().trim();
                    String hora = etHoraCita.getText().toString().trim();

                    // Validar los campos
                    if (lugar.isEmpty() || nombreDoc.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                        Toast.makeText(getContext(), "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        postCita(lugar, nombreDoc, especialidad, fecha, hora);
                    }
                }
            });
        } else {
            getCita();
            btnAgendar.setText("Actualizar");
            btnAgendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Recoger los datos de la cita médica
                    String lugar = etLugar.getText().toString().trim();
                    String nombreDoc = etNombreDoc.getText().toString().trim();
                    String especialidad = etEspecialidad.getText().toString().trim();
                    String fecha = etFechaCita.getText().toString().trim();
                    String hora = etHoraCita.getText().toString().trim();

                    // Validar los campos
                    if (lugar.isEmpty() || nombreDoc.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                        Toast.makeText(getContext(), "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        updateCita(lugar, nombreDoc, especialidad, fecha, hora);
                    }
                }
            });
        }

        return view;
    }

    private void postCita(String lugar, String nombreDoc, String especialidad, String fecha, String hora) {
        Map<String, Object> cita = new HashMap<>();
        cita.put("lugar", lugar);
        cita.put("nombreDoc", nombreDoc);
        cita.put("especialidad", especialidad);
        cita.put("fecha", fecha);
        cita.put("hora", hora);

        mfirestore.collection("citas").add(cita).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Cita agendada con éxito", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().beginTransaction().replace(R.id.contenedor, new AgendaFragment()).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al agendar la cita", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCita(String lugar, String nombreDoc, String especialidad, String fecha, String hora) {
        Map<String, Object> cita = new HashMap<>();
        cita.put("lugar", lugar);
        cita.put("nombreDoc", nombreDoc);
        cita.put("especialidad", especialidad);
        cita.put("fecha", fecha);
        cita.put("hora", hora);

        mfirestore.collection("citas").document(id_cita).update(cita).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Cita actualizada con éxito", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().beginTransaction().replace(R.id.contenedor, new AgendaFragment()).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al actualizar la cita", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCita() {
        mfirestore.collection("citas").document(id_cita).get().addOnSuccessListener(documentSnapshot -> {
            String lugar = documentSnapshot.getString("lugar");
            String nombreDoc = documentSnapshot.getString("nombreDoc");
            String especialidad = documentSnapshot.getString("especialidad");
            String fecha = documentSnapshot.getString("fecha");
            String hora = documentSnapshot.getString("hora");

            etLugar.setText(lugar);
            etNombreDoc.setText(nombreDoc);
            etEspecialidad.setText(especialidad);
            etFechaCita.setText(fecha);
            etHoraCita.setText(hora);
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
        });
    }
}

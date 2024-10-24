package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarMedicamentoFragment extends Fragment {


    Button btn_add;
    EditText nombre, tipo, intensidad, horaam, horapm;
    Spinner frecuencia;
    private FirebaseFirestore mfirestore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_agregar_medicamento, container, false);

        // Manejar EdgeToEdge y los insets de la barra del sistema
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mfirestore = FirebaseFirestore.getInstance();

        nombre = view.findViewById(R.id.et_nombre_med);
        tipo = view.findViewById(R.id.et_tipo_med);
        intensidad = view.findViewById(R.id.et_intensidad_med);

        frecuencia = view.findViewById(R.id.spinnerFrecuencia);

        horaam = view.findViewById(R.id.et_hora_am);
        horapm = view.findViewById(R.id.et_hora_pm);

        btn_add = view.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombremed = nombre.getText().toString().trim();
                String tipomed =  tipo.getText().toString().trim();
                String intensidadmed = intensidad.getText().toString().trim();
                String frecuenciamed = frecuencia.getSelectedItem().toString();
                String ammed = horaam.getText().toString().trim();
                String pmmed = horaam.getText().toString().trim();

                if(nombremed.isEmpty() && tipomed.isEmpty() && intensidadmed.isEmpty() && frecuenciamed.isEmpty() && ammed.isEmpty() && pmmed.isEmpty()){
                    Toast.makeText(getContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postMed(nombremed, tipomed, intensidadmed, frecuenciamed, ammed, pmmed);
                }

            }
        });


//        // Configurar el botón "Listo" para reemplazar el fragmento actual
//        view.findViewById(R.id.btn_add).setOnClickListener(v -> {
//            // Reemplazar con el fragmento "MedicamentosFragment"
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//            transaction.replace(R.id.contenedor, new MedicamentosFragment());
//            transaction.addToBackStack(null);  // Para permitir volver atrás
//            transaction.commit();
//        });

        return view;
    }

    private void postMed(String nombremed, String tipomed, String intensidadmed, String frecuenciamed, String ammed, String pmmed) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombremed);
        map.put("tipo", tipomed);
        map.put("intensidad", intensidadmed);
        map.put("frecuencia", frecuenciamed);
        map.put("hora_am", ammed);
        map.put("hora_pm", pmmed);



        mfirestore.collection("medicamentos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Guardada con exito", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, new MedicamentosFragment());
                transaction.commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al ignresar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

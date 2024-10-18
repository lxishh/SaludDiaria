package com.example.saluddiaria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class EmergenciasFragment extends Fragment {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private LinearLayout linearLayoutNumeros;
    private FirebaseFirestore db;
    private CollectionReference emergencyNumbersRef;

    public EmergenciasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializa Firestore
        db = FirebaseFirestore.getInstance();
        emergencyNumbersRef = db.collection("numeros_emergencia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_emergencias, container, false);

        // Manejar EdgeToEdge y los insets de la barra del sistema
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar el LinearLayout
        linearLayoutNumeros = view.findViewById(R.id.linearLayoutNumeros);
        EditText editTextNumeroEmergencia = view.findViewById(R.id.editTextNumeroEmergencia);
        Button buttonAgregarContacto = view.findViewById(R.id.btnAgregarContacto);

        // Cargar números de emergencia al iniciar
        loadEmergencyNumbers();

        // Configurar botones de llamada
        setupCallButtons(view);

        buttonAgregarContacto.setOnClickListener(v -> {
            String phoneNumber = editTextNumeroEmergencia.getText().toString().trim();
            if (!phoneNumber.isEmpty()) {
                addEmergencyNumber(phoneNumber);
                editTextNumeroEmergencia.setText(""); // Limpiar el campo después de agregar
            } else {
                // Mostrar mensaje de error si el campo está vacío
                Toast.makeText(getContext(), "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private List<String> existingNumbers = new ArrayList<>();

    private void loadEmergencyNumbers() {
        emergencyNumbersRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                existingNumbers.clear(); // Limpiar la lista antes de cargar los números
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String phoneNumber = document.getString("number");
                    if (phoneNumber != null) {
                        existingNumbers.add(phoneNumber); // Añadir a la lista
                        // No llames a addEmergencyNumber aquí
                        // Agregar el número al LinearLayout directamente
                        addNumberToLayout(phoneNumber);
                    }
                }
            } else {
                Toast.makeText(getContext(), "Error al cargar números", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNumberToLayout(String phoneNumber) {
        // Crear un TextView para mostrar el número
        TextView textViewNumero = new TextView(getContext());
        textViewNumero.setText(phoneNumber);
        textViewNumero.setTextSize(18);
        textViewNumero.setPadding(8, 8, 8, 8);

        // Centrar el texto
        textViewNumero.setGravity(Gravity.CENTER);

        // Ajustar el ancho del TextView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textViewNumero.setLayoutParams(params);

        textViewNumero.setOnClickListener(v -> makeCall(phoneNumber)); // Hacer la llamada al hacer clic

        // Agregar el TextView al LinearLayout
        linearLayoutNumeros.addView(textViewNumero);
    }




    private void addEmergencyNumber(String phoneNumber) {
        // Verificar si el número ya existe
        if (existingNumbers.contains(phoneNumber)) {
            Toast.makeText(getContext(), "Número ya existe", Toast.LENGTH_SHORT).show();
            return; // Salir si el número ya está registrado
        }

        // Crear un TextView para mostrar el número
        TextView textViewNumero = new TextView(getContext());
        textViewNumero.setText(phoneNumber);
        textViewNumero.setTextSize(18);
        textViewNumero.setPadding(8, 8, 8, 8);
        textViewNumero.setOnClickListener(v -> makeCall(phoneNumber)); // Hacer la llamada al hacer clic

        // Agregar el TextView al LinearLayout
        linearLayoutNumeros.addView(textViewNumero);

        // Guardar el número en Firestore
        saveEmergencyNumber(phoneNumber);
    }


    private void saveEmergencyNumber(String phoneNumber) {
        Map<String, Object> numberData = new HashMap<>();
        numberData.put("number", phoneNumber);

        emergencyNumbersRef.add(numberData).addOnSuccessListener(documentReference -> {
            // Número guardado exitosamente
            Toast.makeText(getContext(), "Número guardado", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            // Manejo de errores
            Toast.makeText(getContext(), "Error al guardar número", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupCallButtons(View view) {
        Button buttonSamu = view.findViewById(R.id.button5);
        Button buttonBomberos = view.findViewById(R.id.button18);
        Button buttonCarabineros = view.findViewById(R.id.button19);

        buttonSamu.setOnClickListener(v -> makeCall("131")); // Cambia "131" por el número real
        buttonBomberos.setOnClickListener(v -> makeCall("132")); // Cambia "132" por el número real
        buttonCarabineros.setOnClickListener(v -> makeCall("133")); // Cambia "133" por el número real
    }

    private void makeCall(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado
                // Aquí puedes agregar lógica si es necesario
            }
        }
    }
}

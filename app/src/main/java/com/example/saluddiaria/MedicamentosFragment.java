package com.example.saluddiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MedicamentosFragment extends Fragment {

    public MedicamentosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_medicamentos, container, false);

        // Manejar EdgeToEdge y los insets de la barra del sistema
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el botón para abrir el fragmento de "AgregarMedicamento"
        view.findViewById(R.id.btnAgregarMedicamento).setOnClickListener(v -> {
            // Reemplazar con el fragmento "AgregarMedicamento"
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor, new AgregarMedicamentoFragment());
            transaction.addToBackStack(null);  // Para permitir volver atrás
            transaction.commit();
        });

        return view;
    }
}

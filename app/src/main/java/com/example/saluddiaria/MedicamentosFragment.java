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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saluddiaria.adapter.MedicamentoAdapter;
import com.example.saluddiaria.model.Medicamento;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MedicamentosFragment extends Fragment {


    RecyclerView mRecycler;
    MedicamentoAdapter mAdapter;
    FirebaseFirestore mFirestore;

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

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = view.findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = mFirestore.collection("medicamentos");

        FirestoreRecyclerOptions<Medicamento> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Medicamento>().setQuery(query, Medicamento.class).build();

        mAdapter = new MedicamentoAdapter(firestoreRecyclerOptions, getContext());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);


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

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

}

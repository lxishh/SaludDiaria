package com.example.saluddiaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saluddiaria.R;
import com.example.saluddiaria.model.Medicamento;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MedicamentoAdapter extends FirestoreRecyclerAdapter<Medicamento, MedicamentoAdapter.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private Context context;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MedicamentoAdapter(@NonNull FirestoreRecyclerOptions<Medicamento> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Medicamento Medicamento) {

        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        //Mostrar los campos referenciados, llamarlos para mostrar
        //Leyendo los datos en la base de datos
        viewHolder.nombre.setText(Medicamento.getNombre());
        viewHolder.tipo.setText(Medicamento.getTipo());
        viewHolder.intensidad.setText(Medicamento.getIntensidad());
        viewHolder.frecuencia.setText(Medicamento.getFrecuencia());

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMedicamento(id);

            }
        });
    }

    private void deleteMedicamento(String id) {
        mFirestore.collection("medicamentos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Eliminado con exito", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // !!! Inflar datos: recuperar layout
        // Mostrando
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicamento, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Referenciar cada uno de los campos
        //Instancias, pasando a traves del setText
        TextView nombre, tipo, intensidad, frecuencia;
        ImageView btn_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tvNombreMed);
            tipo = itemView.findViewById(R.id.tvTipoMed);
            intensidad = itemView.findViewById(R.id.tvIntensidadMed);
            frecuencia = itemView.findViewById(R.id.tvFrecuenciaMed);

            btn_delete = itemView.findViewById(R.id.btn_eliminarCard);

        }
    }
}

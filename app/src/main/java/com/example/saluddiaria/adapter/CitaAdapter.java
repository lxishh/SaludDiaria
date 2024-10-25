package com.example.saluddiaria.adapter;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saluddiaria.CitaMedicaFragment; // Cambia esto al nombre del fragmento de agregar cita
import com.example.saluddiaria.R;
import com.example.saluddiaria.model.Cita;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CitaAdapter extends FirestoreRecyclerAdapter<Cita, CitaAdapter.CitaViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private Context context;
    FragmentManager fm;

    public CitaAdapter(@NonNull FirestoreRecyclerOptions<Cita> options, Context context, FragmentManager fm) {
        super(options);
        this.context = context;
        this.fm = fm;
    }

    @Override
    protected void onBindViewHolder(@NonNull CitaViewHolder holder, int i, @NonNull Cita Cita) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getBindingAdapterPosition());
        final String id = documentSnapshot.getId();

        // Mostrar los campos referenciados
        holder.tvLugar.setText(Cita.getLugar());
        holder.tvNombre.setText(Cita.getNombreDoc());
        holder.tvEspecialidad.setText(Cita.getEspecialidad());
        holder.tvFecha.setText(Cita.getFecha());
        holder.tvHora.setText(Cita.getHora());

        holder.btnEliminar.setOnClickListener(v -> {
            eliminarCita(id);
        });

        holder.itemView.setOnClickListener(v -> {
            // Abre el fragmento para editar cita
            CitaMedicaFragment citaMedicaFragment = new CitaMedicaFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id_cita", id);
            citaMedicaFragment.setArguments(bundle);
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.contenedor, citaMedicaFragment); // Asegúrate de usar el ID correcto del contenedor
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void eliminarCita(String id) {
        mFirestore.collection("citas").document(id).delete()
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Cita eliminada con éxito", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, "Error al eliminar la cita", Toast.LENGTH_SHORT).show());
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tarjeta, parent, false);
        return new CitaViewHolder(view);
    }

    public static class CitaViewHolder extends RecyclerView.ViewHolder {
        TextView tvLugar, tvNombre, tvEspecialidad, tvFecha, tvHora;
        ImageView btnEliminar, btnEditar;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLugar = itemView.findViewById(R.id.tvLugar);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);

            btnEliminar = itemView.findViewById(R.id.btnEliminarTarjeta);
            btnEditar = itemView.findViewById(R.id.btn_editar_Tarjeta);
        }
    }
}

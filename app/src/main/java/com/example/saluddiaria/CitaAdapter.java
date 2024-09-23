package com.example.saluddiaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {
    private ArrayList<Cita> citas;

    public CitaAdapter(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tarjeta, parent, false);
        return new CitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
        Cita cita = citas.get(position);
        holder.tvLugar.setText(cita.getLugar());
        holder.tvNombre.setText(cita.getNombreDoc());
        holder.tvEspecialidad.setText(cita.getEspecialidad());
        holder.tvFecha.setText(cita.getFecha());
        holder.tvHora.setText(cita.getHora());
        holder.btnEliminar.setOnClickListener(v -> {
            eliminarCita(holder.getAdapterPosition());
        });

    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    public static class CitaViewHolder extends RecyclerView.ViewHolder {
        TextView tvLugar, tvNombre, tvEspecialidad, tvFecha, tvHora;
        TextView btnEliminar;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLugar = itemView.findViewById(R.id.tvLugar);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    public void eliminarCita(int position) {
        citas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, citas.size());
    }

}

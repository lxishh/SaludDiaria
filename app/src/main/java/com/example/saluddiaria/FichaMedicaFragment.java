package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FichaMedicaFragment extends Fragment {

    private EditText nombreEditText, padecimientosEditText, detallesEditText, alergiasEditText, medicamentosEditText;
    private Button button;

    public FichaMedicaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ficha_medica, container, false);

        // Referencias a los EditText y Button
        nombreEditText = view.findViewById(R.id.nombreEditText);
        padecimientosEditText = view.findViewById(R.id.padecimientosEditText);
        detallesEditText = view.findViewById(R.id.detallesEditText);
        alergiasEditText = view.findViewById(R.id.alergiasEditText);
        medicamentosEditText = view.findViewById(R.id.medicamentosEditText);
        button = view.findViewById(R.id.button);

        // Configurar el OnClickListener para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados
                String nombre = nombreEditText.getText().toString();
                String padecimientos = padecimientosEditText.getText().toString();
                String detalles = detallesEditText.getText().toString();
                String alergias = alergiasEditText.getText().toString();
                String medicamentos = medicamentosEditText.getText().toString();

                // Cambiar los EditText a no editables
                setEditTextNotEditable(nombreEditText);
                setEditTextNotEditable(padecimientosEditText);
                setEditTextNotEditable(detallesEditText);
                setEditTextNotEditable(alergiasEditText);
                setEditTextNotEditable(medicamentosEditText);

                // Deshabilitar el botón después de guardar
                button.setEnabled(false);
            }
        });

        return view;
    }

    // Método para deshabilitar un EditText
    private void setEditTextNotEditable(EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setClickable(false);
        editText.setBackgroundColor(Color.TRANSPARENT); // Cambiar color de fondo a transparente (opcional)
    }
}

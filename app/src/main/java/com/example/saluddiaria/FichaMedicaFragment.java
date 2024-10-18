package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_ficha_medica, container, false);

        // Referencias a los EditText y Button
        nombreEditText = view.findViewById(R.id.nombreEditText);
        padecimientosEditText = view.findViewById(R.id.padecimientosEditText);
        detallesEditText = view.findViewById(R.id.detallesEditText);
        alergiasEditText = view.findViewById(R.id.alergiasEditText);
        medicamentosEditText = view.findViewById(R.id.medicamentosEditText);
        button = view.findViewById(R.id.button);

        // Configurar el OnClickListener para el botón
        button.setOnClickListener(v -> {
            // Mostrar un mensaje de guardado exitoso
            Toast.makeText(getContext(), "Cambios guardados con éxito", Toast.LENGTH_SHORT).show();

            // Hacer los EditText no editables
            makeEditTextNonEditable(nombreEditText);
            makeEditTextNonEditable(padecimientosEditText);
            makeEditTextNonEditable(detallesEditText);
            makeEditTextNonEditable(alergiasEditText);
            makeEditTextNonEditable(medicamentosEditText);
        });

        // Configurar el click en cada EditText para volverlos editables
        nombreEditText.setOnClickListener(v -> makeEditTextEditable(nombreEditText));
        padecimientosEditText.setOnClickListener(v -> makeEditTextEditable(padecimientosEditText));
        detallesEditText.setOnClickListener(v -> makeEditTextEditable(detallesEditText));
        alergiasEditText.setOnClickListener(v -> makeEditTextEditable(alergiasEditText));
        medicamentosEditText.setOnClickListener(v -> makeEditTextEditable(medicamentosEditText));

        return view;
    }

    // Método para hacer que un EditText no sea editable
    private void makeEditTextNonEditable(EditText editText) {
        editText.setFocusable(false);
        editText.setClickable(true);  // Permitimos que detecte clics
        editText.setCursorVisible(false);  // Ocultamos el cursor
        editText.setBackgroundColor(Color.TRANSPARENT);  // Cambiamos el fondo para indicar que no es editable
        editText.setTextColor(Color.GRAY);  // Cambia el color del texto a gris
    }

    // Método para hacer que un EditText sea editable
    private void makeEditTextEditable(EditText editText) {
        editText.setFocusableInTouchMode(true);  // Permite el enfoque al tocar
        editText.setClickable(false);  // Ya no detecta clics
        editText.setCursorVisible(true);  // Muestra el cursor
        editText.setBackgroundColor(Color.TRANSPARENT);  // Fondo transparente
        editText.setTextColor(Color.BLACK);  // Cambia el color del texto a negro
    }
}

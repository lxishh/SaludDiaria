package com.example.saluddiaria;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando el layout para el fragmento
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Aplicando insets a la vista como se hacía en la actividad
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etPeso = view.findViewById(R.id.etPeso);
        EditText etAltura = view.findViewById(R.id.etAltura);
        EditText etEdad = view.findViewById(R.id.etEdad);

        // Configurando el botón de editar
        view.findViewById(R.id.button4).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Se envió un correo con los pasos a seguir.", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.button14).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Se envió un correo con los pasos a seguir.", Toast.LENGTH_SHORT).show();
        });

        // Configurando el botón "Listo"
        view.findViewById(R.id.button16).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Cambios guardados con éxito", Toast.LENGTH_SHORT).show();

            // Hacer los EditText no editables
            makeEditTextNonEditable(etPeso);
            makeEditTextNonEditable(etAltura);
            makeEditTextNonEditable(etEdad);

        });

        // Configurando el click en cada EditText para volverlos editables
        etPeso.setOnClickListener(v -> makeEditTextEditable(etPeso));
        etAltura.setOnClickListener(v -> makeEditTextEditable(etAltura));
        etEdad.setOnClickListener(v -> makeEditTextEditable(etEdad));

        return view;
    }



    private void makeEditTextNonEditable(EditText editText) {
        editText.setFocusable(false);
        editText.setClickable(true);  // Permitimos que detecte clics
        editText.setCursorVisible(false);  // Ocultamos el cursor
        editText.setBackgroundColor(Color.TRANSPARENT);  // Cambiamos el fondo si deseas hacerlos parecer no editables
        editText.setTextColor(Color.GRAY);  // Cambia el color del texto a gris
    }

    private void makeEditTextEditable(EditText editText) {
        editText.setFocusableInTouchMode(true);  // Permite el enfoque al tocar
        editText.setClickable(false);  // Ya no detecta clics
        editText.setCursorVisible(true);  // Muestra el cursor
        editText.setBackgroundColor(Color.TRANSPARENT);  // Usa un color de fondo transparente
        editText.setTextColor(Color.BLACK);  // Cambia el color del texto a negro
    }
}
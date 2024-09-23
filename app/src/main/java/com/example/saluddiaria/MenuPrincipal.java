package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton = findViewById(R.id.btnImagen);
        imageButton.setMinimumWidth(100); // Establece un ancho mínimo en píxeles
        imageButton.setMinimumHeight(100); // Establece una altura mínima en píxeles

    }

    public void rAjustes(View v){
        Intent i = new Intent(this, Ajustes.class);
        startActivity(i);
    }

    public void rCalendario(View v){
        Intent i = new Intent(this, Calendario.class);
        startActivity(i);
    }

    public void rHidratacion(View v){
        Intent i = new Intent(this, Hidratacion.class);
        startActivity(i);
    }


    public void rFichaMedica(View v){
        Intent i = new Intent(this, FichaMedica.class);
        startActivity(i);
    }

    public void rEmergenciaSOS(View v){
        Intent i = new Intent(this, EmergenciaSOS.class);
        startActivity(i);
    }

    public void rMedicamentos(View v){
        Intent i = new Intent(this, Medicamentos.class);
        startActivity(i);
    }
}
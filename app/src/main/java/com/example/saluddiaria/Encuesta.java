package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Encuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encuesta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void EnviarForm(View v){
        //Recuperar view
        Spinner sp1 = (Spinner) findViewById(R.id.respuesta1);
        Spinner sp2 = (Spinner) findViewById(R.id.respuesta2);
        Spinner sp3 = (Spinner) findViewById(R.id.respuesta3);
        Spinner sp4 = (Spinner) findViewById(R.id.respuesta4);
        Spinner sp5 = (Spinner) findViewById(R.id.respuesta5);
        //Recuperar valor
        String respuesta1 = sp1.getSelectedItem().toString();
        String respuesta2 = sp1.getSelectedItem().toString();
        String respuesta3 = sp1.getSelectedItem().toString();
        String respuesta4 = sp1.getSelectedItem().toString();
        String respuesta5 = sp1.getSelectedItem().toString();

        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }


}
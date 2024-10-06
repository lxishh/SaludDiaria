package com.example.saluddiaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OlvidarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_olvidar_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Recuperar(View v){

        EditText etCorreo = (EditText) findViewById(R.id.etEmail);
        String correo = etCorreo.getText().toString();

        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un correo", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Correo enviado", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
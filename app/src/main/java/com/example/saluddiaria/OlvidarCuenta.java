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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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

    public void Recuperar(View v) {

        //Buscar por ID el campo donde escribimos (es un edit text)
        EditText etCorreo = (EditText) findViewById(R.id.etEmail);
        // etCorreo es el nombre que le damos al campo donde escribimos

        // Definimos tipo de dato y nombre de la variable
        // Recuperamos el texto que haya en etCorreo y lo volvemos texto y luego string
        String correo = etCorreo.getText().toString();

        //Validacion de que haya algo escrito en el campo
        if (correo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un correo", Toast.LENGTH_SHORT).show();
            return;
        }

        //Conexion a FireStore (BD)

        //Se crea ''db'' de tipo FirebaseFirestore y se utiliza el metodo para obtener la instancia y asi interactuar con la BD
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Se realiza una consulta a la colección llamada "usuarios"
        // y se busca un documento cuyo campo correo coincida con el valor de la variable correo (EditText).
        db.collection("usuarios")
                .whereEqualTo("correo", correo)
                .get()
                //Una vez que se realice la tarea:
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean correoExiste = false;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            correoExiste = true; // Si hay al menos un documento, el correo existe
                            break; // Salir del bucle, no necesitamos más de un documento
                        }

                        if (correoExiste) {
                            // Aquí se envia el correo de recuperación
                            Toast.makeText(this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(this, "El correo no está registrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error al verificar el correo", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
package com.example.saluddiaria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fotoLogo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View v) {
        EditText campo1 = this.findViewById(R.id.correo);
        String correo = campo1.getText().toString().trim();

        EditText campo2 = this.findViewById(R.id.contrasenia);
        String contrasenia = campo2.getText().toString().trim();

        // Validar campos
        if (correo.isEmpty() || contrasenia.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el usuario existe en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .whereEqualTo("correo", correo)
                .whereEqualTo("contrasenia", contrasenia) // Comprueba que la contraseña también coincida
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            // El usuario existe, procede a la siguiente actividad
                            CheckBox cbRecuerdame = findViewById(R.id.cbRecuerdame);
                            boolean chequeado = cbRecuerdame.isChecked();
                            if (chequeado) {
                                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                                SharedPreferences.Editor editor = datos.edit();
                                editor.putString("correo", correo);
                                editor.apply();
                            }

                            Intent i = new Intent(this, horaEncuesta.class);
                            startActivity(i);
                        } else {
                            // Las credenciales son incorrectas
                            Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Error al realizar la consulta
                        Toast.makeText(this, "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void recuperarPass(View V){
        Intent i = new Intent(this, OlvidarCuenta.class);
        startActivity(i);
    }

    public void registrarCuenta(View V){
        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String correo = datos.getString("correo", "");
        if(!correo.equals("")){
            Intent i = new Intent(this, horaEncuesta.class);
            startActivity(i);
        }

    }
}
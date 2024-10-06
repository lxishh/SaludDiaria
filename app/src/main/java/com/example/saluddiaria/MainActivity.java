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

    public void login(View V){

        EditText campo1 = this.findViewById(R.id.correo);
        String correo = campo1.getText().toString();

        EditText campo2 = this.findViewById(R.id.contrasenia);
        String contrasenia = campo2.getText().toString();

        if(correo.equals("admin") && contrasenia.equals("admin")){
            CheckBox cbRecuerdame = (CheckBox) findViewById(R.id.cbRecuerdame);
            boolean chequeado = cbRecuerdame.isChecked();
            if(chequeado==true){
                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = datos.edit();
                editor.putString("correo", correo);
                editor.apply();
            }


            Intent i = new Intent(this, horaEncuesta.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
        }
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
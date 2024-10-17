package com.example.saluddiaria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //Referencia al toolbar (layout)
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Codificar que se ejecutara cuando le das tap a un tab
                
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        CalendarioFragment c = new CalendarioFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, c).commit();
                        break;
                    case 1:
                        HidratacionFragment h = new HidratacionFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,h).commit();
                        break;
                    case 2:
                        MedicamentosFragment m = new MedicamentosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,m).commit();
                        break;
                    case 3:
                        FichaMedicaFragment f = new FichaMedicaFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
                        break;
                    case 4:
                        EmergenciasFragment e = new EmergenciasFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,e).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Codificar cosas a ejecutar cuando un tab deja de estar seleccionado
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Codificar cosas cuando se vuelva a seleccionar
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Incoporar el menu dentro del activity
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); //Recuperando el id de la opcion seleccionada
        if(id==R.id.op1){
            PerfilFragment perfilFragment = new PerfilFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, perfilFragment).commit();
        }
        else if (id==R.id.op2) {
            NotificacionesFragment notificacionesFragment = new NotificacionesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, notificacionesFragment).commit();
        } else if (id==R.id.op3) {
            Toast.makeText(this, "Hora Encuesta", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op4) {
            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = datos.edit();
            editor.remove("correo");
            editor.apply();
            Toast.makeText(this, "Sesion cerrada con exito", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
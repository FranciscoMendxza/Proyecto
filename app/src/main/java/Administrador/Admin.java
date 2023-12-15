package Administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import mx.edu.tesoem.isc.proyecto.R;

public class Admin extends AppCompatActivity {

    Button usuarios, preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        usuarios = findViewById(R.id.btnlistar);
        preguntas = findViewById(R.id.btncapturar);

        usuarios.setOnClickListener(v -> {
            Intent lanza = new Intent(this, Usuarios.class);
            startActivity(lanza);
        });

        preguntas.setOnClickListener(v -> {
            Intent lanza = new Intent(this, Preguntas.class);
            startActivity(lanza);
        });
    }
}
package mx.edu.tesoem.isc.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import Administrador.Admin;
import Registro.Usuario;

public class Principal extends AppCompatActivity {

    Button admin, registra;
    private static final int PERMISO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        admin = findViewById(R.id.btnadmin);
        registra = findViewById(R.id.btnreg);
        registra.setEnabled(false);

        admin.setOnClickListener(v -> {
            Intent lanza = new Intent(this, Admin.class);
            startActivity(lanza);
        });

        registra.setOnClickListener(v -> {
            Intent lanza = new Intent(Principal.this, Usuario.class);
            startActivity(lanza);
        });

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMISO);
        }else{
            registra.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registra.setEnabled(true);
            } else {
                registra.setEnabled(false);
            }
        }
    }
}
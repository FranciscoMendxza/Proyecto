package Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Almacen.Conexion;
import DTOs.DatosUser;
import Exa.Examen;
import mx.edu.tesoem.isc.proyecto.Principal;
import mx.edu.tesoem.isc.proyecto.R;

public class Usuario extends AppCompatActivity {
    Button save;
    EditText nombre;
    TextView lat, lon;
    int id = 0;
    List<DatosUser> ldatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Conexion conexion = new Conexion();
        if (conexion.Leer(this)){
            ldatos = conexion.getDatosUser();
        }

        save = findViewById(R.id.btnsave);
        nombre = findViewById(R.id.ednombre);

        lat = findViewById(R.id.txtlat);
        lon = findViewById(R.id.txtlong);

        startService(new Intent(this, LocationService.class));

        save.setOnClickListener(view -> {
            grabar();

            Intent preg = new Intent(getApplicationContext(), Principal.class);
            startActivity(preg);

            stopService(new Intent(this, LocationService.class));
        });
    }

    public void grabar(){

       DatosUser datos = new DatosUser();
       datos.setId(DatosUser.getNextId());
       datos.setNombre(nombre.getText().toString());
       datos.setLatitud(Double.parseDouble(lat.getText().toString()));
       datos.setLongitud(Double.parseDouble(lon.getText().toString()));

       int existingIndex = ldatos.indexOf(datos);

       if (existingIndex != -1){
           ldatos.set(existingIndex, datos);
       }else{
           ldatos.add(datos);
       }

       Conexion conexion = new Conexion();
       if (conexion.Grabar(this, ldatos)) {
           Toast.makeText(this, "Se grabo con éxito", Toast.LENGTH_SHORT).show();
       } else {
           Toast.makeText(this, "No se grabaron los datos", Toast.LENGTH_SHORT).show();
       }
    }


    private final BroadcastReceiver ubicacionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("ubicacion_actualizada".equals(intent.getAction())){
                double latitud = intent.getDoubleExtra("latitud", 0.0);
                double longitud = intent.getDoubleExtra("longitud", 0.0);

                actualizarUbicacionEnTextView(latitud, longitud);
            }
        }
    };

    public void actualizarUbicacionEnTextView(double latitud, double longitud){
        lat.setText(String.valueOf(latitud));
        lon.setText(String.valueOf(longitud));
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter("ubicacion_actualizada");
        registerReceiver(ubicacionReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(ubicacionReceiver);
    }


}

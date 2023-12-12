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

import java.util.ArrayList;
import java.util.List;

import DTOs.DatosU;
import Exa.Examen;
import mx.edu.tesoem.isc.proyecto.R;

public class Usuario extends AppCompatActivity {
    Button save;
    EditText nombre;
    TextView lat, lon;
    List<DatosU> listaDatos = new ArrayList<>();
    private double currentLatitude;
    private double currentLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        save = findViewById(R.id.btnsave);
        nombre = findViewById(R.id.ednombre);

        lat = findViewById(R.id.txtlat);
        lon = findViewById(R.id.txtlong);

        startService(new Intent(this, LocationService.class));

        save.setOnClickListener(view -> {
            Intent preg = new Intent(getApplicationContext(), Examen.class);
            preg.putExtra("nombre", nombre.getText().toString());
            preg.putExtra("latitud", Double.parseDouble(String.valueOf(currentLatitude)));
            preg.putExtra("longitud", Double.parseDouble(String.valueOf(currentLongitude)));
            startActivity(preg);
        });

        // Registrar un receptor de difusión para recibir actualizaciones de ubicación
        IntentFilter filter = new IntentFilter("location-update");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentLatitude = intent.getDoubleExtra("latitud", 0.0);
                currentLongitude = intent.getDoubleExtra("longitud", 0.0);
            }
        };
        registerReceiver(receiver, filter);
    }

}

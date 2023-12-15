package Administrador;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Registro.LocationService;
import mx.edu.tesoem.isc.proyecto.R;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    GoogleMap mMap;
    TextView txtcalif, latitu, longitu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtcalif = findViewById(R.id.txtcalif);
        latitu = findViewById(R.id.testlatitud);
        longitu = findViewById(R.id.testlongitud);

        Intent serviceIntent = new Intent(this, LocationService.class);
        startService(serviceIntent);

        // Recuperar datos del Intent
        Intent intent = getIntent();
        String latitud = intent.getStringExtra("latitud");
        String longitud = intent.getStringExtra("longitud");
        String calif = (intent.getStringExtra("calif"));

        // Mostrar la calificación en el TextView
        txtcalif.setText("La calificación obtenida fue " + calif);
        latitu.setText("Latitud: " + latitud);
        longitu.setText("Longitud: " + longitud);

        initMap(latitud, longitud);
    }

    private void initMap(String latitud, String longitud) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        IntentFilter intentFilter = new IntentFilter("location-update");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Actualizar el marcador con la ubicación proporcionada
                double latitude = Double.parseDouble(latitud);
                double longitude = Double.parseDouble(longitud);

                LatLng currentLocation = new LatLng(latitude, longitude);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicacion aproximada"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            // Verificar si los permisos fueron concedidos.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "concedido", Toast.LENGTH_SHORT).show();
                // Permiso de ubicación concedido, inicializar el mapa.
                initMap("0.0", "0.0"); // Valores predeterminados si no se proporciona una ubicación
            } else {
                Toast.makeText(this, "no concedido", Toast.LENGTH_SHORT).show();
                // Permiso de ubicación no concedido. Puedes manejar esto mostrando un mensaje al usuario.
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }
}

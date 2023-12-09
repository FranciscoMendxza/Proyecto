package Registro;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LocationService extends Service {

    private static final String TAG = "LocationService";
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Override
    public void onCreate() {
        super.onCreate();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Configurar la solicitud de ubicación
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(500); // Intervalo de actualización en milisegundos (aquí, 10 segundos)
        locationRequest.setFastestInterval(500); // Intervalo más rápido
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Configurar el callback de ubicación
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Manejar la ubicación obtenida, por ejemplo, actualizar los TextView
                    double latitud = location.getLatitude();
                    double longitud = location.getLongitude();
                    actualizarUbicacionEnTextView(latitud, longitud);
                }
            }
        };

        // Solicitar actualizaciones de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado");
        Toast.makeText(this, "Servicio Iniciado", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Detener las actualizaciones de ubicación cuando el servicio se destruye
        if (fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
        Log.d(TAG, "Servicio detenido");
        Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void actualizarUbicacionEnTextView(double latitud, double longitud) {
        // Actualizar tus TextView (txtlatitud y txtlongitud) con los valores de latitud y longitud
        Log.d(TAG, "Latitud: " + latitud + ", Longitud: " + longitud);

        Intent intent = new Intent("ubicacion_actualizada");
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        sendBroadcast(intent);
    }
}

package Administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import Almacen.Conexion;
import DTOs.DatosUser;
import mx.edu.tesoem.isc.proyecto.R;

public class Usuarios extends AppCompatActivity {

    GridView vista;
    ArrayList <String> contenidogv = new ArrayList<>();
    ArrayAdapter adaptador;
    List<DatosUser> listadatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        vista = findViewById(R.id.gvdatos);
        lee();
    }

    private void lee(){
        String cadena = "";
        Conexion conexion = new Conexion();
        if (conexion.Leer(this)){
            listadatos = conexion.getDatosUser();
            contenidogv.add("ID");
            contenidogv.add("Nombre");
            contenidogv.add("Latitud");
            contenidogv.add("Longitud");
            contenidogv.add("Vista");

            if (listadatos.size() > 0 ){
                for (DatosUser datos : listadatos){
                    contenidogv.add(datos.getNombre());
                    contenidogv.add(String.valueOf(Double.parseDouble(String.valueOf(datos.getLatitud()))));
                    contenidogv.add(String.valueOf(Double.parseDouble(String.valueOf(datos.getLongitud()))));
                }
            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contenidogv);
            vista.setAdapter(adaptador);
        }
    }
}
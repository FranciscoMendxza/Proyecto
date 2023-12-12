package Administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import Almacen.ConexionU;
import DTOs.DatosU;
import mx.edu.tesoem.isc.proyecto.R;

public class Usuarios extends AppCompatActivity {

    GridView gvdatos;
    ArrayList <String> contenidogv = new ArrayList<>();
    CustomAdapter adaptador;
    List<DatosU> listadatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        gvdatos = findViewById(R.id.gvdatos);
        lee();

        gvdatos.setOnItemClickListener((parent, view, i, l) -> {
            int columnIndex = i % 6; // Índice de la columna actual (0 a 5)

            if (columnIndex == 4 && "Ver".equals(contenidogv.get(i))) {
                // Obtén los datos de la línea seleccionada
                String latitud = contenidogv.get(i - 2); // El índice de latitud es i - 2
                String longitud = contenidogv.get(i - 1); // El índice de longitud es i - 1
                String calif = contenidogv.get(i + 1); // El índice de calif es i

                // Crea un Intent para iniciar la actividad MapaActivity
                Intent intent = new Intent(this, MapaActivity.class);

                // Agrega los datos como extras al Intent
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                intent.putExtra("calif", calif);
                startActivity(intent);
            }
        });
    }

    private void lee(){
        ConexionU conexionu = new ConexionU();
        if (conexionu.Leer(this)){
            listadatos = conexionu.getDatosUser();
            contenidogv.add("ID");
            contenidogv.add("Nombre");
            contenidogv.add("Latitud");
            contenidogv.add("Longitud");
            contenidogv.add("Calif");
            contenidogv.add("Vista");

            if (listadatos.size() > 0 ){
                int idC = 1;
                for (DatosU datos : listadatos){
                    contenidogv.add(String.valueOf(idC));
                    idC++;
                    contenidogv.add(datos.getNombre());
                    contenidogv.add(String.valueOf(Double.parseDouble(String.valueOf(datos.getLatitud()))));
                    contenidogv.add(String.valueOf(Double.parseDouble(String.valueOf(datos.getLongitud()))));
                    contenidogv.add("Ver");
                    contenidogv.add(String.valueOf(datos.getCalificacion()));
                }
            }

            adaptador = new CustomAdapter(this, android.R.layout.simple_list_item_1, contenidogv, 6);
            gvdatos.setAdapter(adaptador);
        }
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        private int columnCount; // Variable para almacenar el número total de columnas

        // Constructor que toma el número total de columnas como parámetro
        public CustomAdapter(Context context, int resource, List<String> objects, int columnCount) {
            super(context, resource, objects);
            this.columnCount = columnCount;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            // Calcular la columna actual (0-indexed)
            int currentColumn = position % columnCount;

            // Ocultar la sexta columna
            if (currentColumn == 5) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }

            return view;
        }
    }
}
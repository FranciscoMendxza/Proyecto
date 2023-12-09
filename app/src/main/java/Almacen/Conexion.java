package Almacen;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import Conversiones.Conversion;
import DTOs.DatosUser;
import Registro.Usuario;

public class Conexion {

    String cadena;
    private final String Arch="datosPersonas.txt";
    Conversion conversion = new Conversion();
    List<DatosUser> ldatos = new ArrayList<DatosUser>();
    public boolean Grabar(Context context, List<DatosUser> datos) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(Arch, Context.MODE_PRIVATE));
            Gson gson = new Gson();

            for (DatosUser dato : datos) {
                String json = gson.toJson(dato);
                archivo.write(json + "\n");
            }

            archivo.flush();
            archivo.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean Leer(Context context) {
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(Arch));
            BufferedReader br = new BufferedReader(archivo);
            Gson gson = new Gson();

            ldatos.clear();  // Limpiar la lista antes de cargar nuevos datos

            String linea;
            while ((linea = br.readLine()) != null) {
                DatosUser datosUser = gson.fromJson(linea, DatosUser.class);
                ldatos.add(datosUser);
            }

            archivo.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<DatosUser> getDatosUser(){return ldatos;

    }
}

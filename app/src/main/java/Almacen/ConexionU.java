package Almacen;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import Conversiones.ConversionU;
import DTOs.DatosU;

public class ConexionU {

    String cadena;
    private final String NomArch="Usuario.txt";
    ConversionU conversionu = new ConversionU();
    List<DatosU> listaDatos = new ArrayList<DatosU>();
    public boolean Grabar(Context context, List<DatosU> datos) {
        try {

            String[] archivos = context.fileList();
            if (archivos.length >= 20) {
                // Eliminar los archivos anteriores
                for (int i = 0; i < 20; i++) {
                    context.deleteFile(archivos[i]);
                }
            }

            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(NomArch, Context.MODE_APPEND));
            for (DatosU dato : datos) {
                cadena = conversionu.Cjson(dato);
                archivo.write(cadena + "\n");
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
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(NomArch));
            BufferedReader br = new BufferedReader(archivo);
            while ((cadena = br.readLine()) != null) {
                listaDatos.add(conversionu.Cdto(cadena));
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<DatosU> getDatosUser(){return listaDatos;

    }
}

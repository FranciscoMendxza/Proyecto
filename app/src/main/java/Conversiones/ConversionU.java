package Conversiones;

import com.google.gson.Gson;

import DTOs.DatosU;

public class ConversionU {
    private Gson gson = new Gson();
    public String Cjson(DatosU datos){
        return gson.toJson(datos);
    }

    public DatosU Cdto(String cadena){
        return gson.fromJson(cadena, DatosU.class);
    }
}

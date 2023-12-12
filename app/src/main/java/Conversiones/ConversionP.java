package Conversiones;

import com.google.gson.Gson;

import DTOs.DatosP;

public class ConversionP {
    private final Gson gson = new Gson();
    public String Cjson(DatosP datosp){
        return gson.toJson(datosp);
    }
    public DatosP Cdto(String cadena){
        return gson.fromJson(cadena, DatosP.class);
    }
}
package Conversiones;

import com.google.gson.Gson;

import DTOs.DatosUser;

public class Conversion {
    private final Gson gson = new Gson();
    public String CJson(DatosUser datos){

        return gson.toJson(datos);
    }

    public DatosUser Cdto(String cadena){
        return gson.fromJson(cadena, DatosUser.class);
    }
}

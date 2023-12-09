package DTOs;

import androidx.annotation.Nullable;

import java.util.Objects;

public class DatosUser {
    int id;
    String nombre;
    double latitud, longitud;

    private static int nextId = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosUser datosUser = (DatosUser) o;
        return id == datosUser.id &&
                Double.compare(datosUser.latitud, latitud) == 0 &&
                Double.compare(datosUser.longitud, longitud) == 0 &&
                Objects.equals(nombre, datosUser.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, latitud, longitud);
    }

    public static int getNextId(){
        return nextId++;
    }

    public DatosUser() {
    }

    public DatosUser(int id, String nombre, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}

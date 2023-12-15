package DTOs;

public class DatosU {
    String nombre, latitud, longitud, calificacion;

    public DatosU(String nombre, String latitud, String longitud, String calificacion) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getCalificacion() {
        return calificacion;
    }

}
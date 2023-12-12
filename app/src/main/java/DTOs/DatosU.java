package DTOs;

public class DatosU {
    String nombre, latitud, longitud, calificacion;

    public DatosU() {
    }

    public DatosU(String nombre, String latitud, String longitud, String calificacion) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}

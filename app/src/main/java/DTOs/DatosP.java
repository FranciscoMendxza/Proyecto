package DTOs;
public class DatosP {
    String pregunta, r1, r2, r3, res, respuestaSeleccionada;

    public DatosP() {
    }

    public DatosP(String pregunta, String r1, String r2, String r3, String res) {
        this.pregunta = pregunta;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.res = res;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(String respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }
}

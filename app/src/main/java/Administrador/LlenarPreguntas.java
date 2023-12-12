package Administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Almacen.ConexionP;
import DTOs.DatosP;
import mx.edu.tesoem.isc.proyecto.Principal;
import mx.edu.tesoem.isc.proyecto.R;

public class LlenarPreguntas extends AppCompatActivity {
    Button siguiente, guardar;
    EditText pregunta, r1, r2, r3, rc;
    TextView npregunta;
    private int contador = 1;
    List<DatosP> listaPreguntas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        siguiente = findViewById(R.id.btnsig);
        guardar = findViewById(R.id.btnguarda);



        npregunta = findViewById(R.id.tv1);
        pregunta = findViewById(R.id.txtpregunta);
        r1 = findViewById(R.id.txtr1);
        r2 = findViewById(R.id.txtr2);
        r3 = findViewById(R.id.txtr3);
        rc = findViewById(R.id.txtrc);

        guardar.setEnabled(false);
        npregunta.setText("Pregunta " + contador);

        siguiente.setOnClickListener(v -> {
            agregarPregunta();

            contador++;
            if (contador == 10){
                guardar.setEnabled(true);
                siguiente.setEnabled(false);
            }else{
                siguiente.setEnabled(true);
            }

            pregunta.setText("");
            r1.setText("");
            r2.setText("");
            r3.setText("");
            rc.setText("");

            npregunta.setText("Pregunta " + contador);
        });

        guardar.setOnClickListener(v -> {
            agregarPregunta();
            Intent lanza = new Intent(getApplicationContext(), Principal.class);
            startActivity(lanza);
            Toast.makeText(this, "Se grabaron las preguntas", Toast.LENGTH_SHORT).show();
        });
    }

    private void agregarPregunta(){
        DatosP datosp = new DatosP(pregunta.getText().toString(), r1.getText().toString(), r2.getText().toString(), r3.getText().toString(), rc.getText().toString());
        listaPreguntas.add(datosp);

        ConexionP conexionp = new ConexionP();
        if (conexionp.Grabar(getApplicationContext(), listaPreguntas)){
            //Toast.makeText(this, "Se grabó", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "No se grabó", Toast.LENGTH_SHORT).show();
        }
    }
}
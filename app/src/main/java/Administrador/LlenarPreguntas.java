package Administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DTOs.Datos;
import mx.edu.tesoem.isc.proyecto.Principal;
import mx.edu.tesoem.isc.proyecto.R;

public class LlenarPreguntas extends AppCompatActivity {
    Button siguiente, guardar;
    EditText pregunta, r1, r2, r3, rc;
    TextView npregunta;
    int contador = 1;
    List<Datos> listaPreguntas = new ArrayList<>();

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
            if (!pregunta.getText().toString().isEmpty() && !r1.getText().toString().isEmpty() && !r2.getText().toString().isEmpty() &&
                !r3.getText().toString().isEmpty() && !rc.getText().toString().isEmpty()){
                agregarPregunta();

                contador++;
                npregunta.setText("Pregunta " + contador);

                pregunta.setText("");
                r1.setText("");
                r2.setText("");
                r3.setText("");
                rc.setText("");

                if (contador == 2){
                    guardar.setEnabled(true);
                    siguiente.setEnabled(false);
                }
            }
        });

        guardar.setOnClickListener(v -> {
            agregarPregunta();
            guardarArchivo();
            Intent lanza = new Intent(this, Principal.class);
            startActivity(lanza);
            Toast.makeText(this, "Se guardaron las preguntas", Toast.LENGTH_SHORT).show();
        });
    }

    private void guardarArchivo(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String preguntasJson = gson.toJson(listaPreguntas);
        String nomarch = "preguntas.json";

        try (FileWriter writer = new FileWriter(getFilesDir().getPath() + "/" + nomarch)){
            writer.write(preguntasJson);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void agregarPregunta(){
        Datos nuevaPregunta = new Datos();

        nuevaPregunta.setPregunta(pregunta.getText().toString());
        nuevaPregunta.setR1(r1.getText().toString());
        nuevaPregunta.setR2(r2.getText().toString());
        nuevaPregunta.setR3(r3.getText().toString());
        nuevaPregunta.setRc(rc.getText().toString());
        listaPreguntas.add(nuevaPregunta);
    }
}
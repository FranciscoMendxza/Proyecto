package Exa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DTOs.Datos;
import mx.edu.tesoem.isc.proyecto.R;

public class Examen extends AppCompatActivity {

    Button siguiente, anterior, terminar;
    TextView pregunta;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3;
    ArrayList<Datos> listadatos = new ArrayList<>();
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        siguiente = findViewById(R.id.btnsiguiente);
        anterior = findViewById(R.id.btnanterior);
        terminar = findViewById(R.id.btnterminar);

        pregunta = findViewById(R.id.tvpregunta);
        rg = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        siguiente.setEnabled(false);
        terminar.setEnabled(false);
        anterior.setEnabled(false);

        siguiente.setOnClickListener(view -> {
            String seleccion = seleccionr();
            if(seleccion != null){
                listadatos.get(contador).setSeleccion(seleccion);
            }
            contador++;
            if (contador < listadatos.size()){
                Datos siguientep = listadatos.get(contador);
                pregunta.setText(siguientep.getPregunta());
                rb1.setText(siguientep.getR1());
                rb2.setText(siguientep.getR2());
                rb3. setText(siguientep.getR3());

                rg.clearCheck();
            }

        });

        rg.setOnCheckedChangeListener((RadioGroupp, i) -> {
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                if (contador == 2){
                    siguiente.setEnabled(false);
                    terminar.setEnabled(true);
                }else{
                    siguiente.setEnabled(true);
                }
                anterior.setEnabled(contador != 0);
            }else{
                siguiente.setEnabled(false);
            }
        });

    }

    private String seleccionr(){
        if (rb1.isChecked()){
            return "a";
        } else if (rb2.isChecked()) {
            return "b";
        } else if (rb3.isChecked()) {
            return "c";
        }else{
            return null;
        }
    }
}
package com.caceres.crudidat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import controllers.AlumnoController;
import model.Alumno;

public class AddActivity extends AppCompatActivity {

    Button btnAdd, btnCancelar;
    EditText etname, etlastname;
    private AlumnoController alumnoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        instanciar();

        Funciones();
    }

    private void Funciones() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear los Errores

                etname.setError(null);
                etlastname.setError(null);
                String name1 = etname.getText().toString();
                String lastname1 = etlastname.getText().toString();

                if ("".equals(name1)) {
                    etname.setError("Escribre el nombre del alumno");
                    etname.requestFocus();
                }

                if ("".equals(lastname1)) {
                    etlastname.setError("Escribre el nombre del alumno");
                    etlastname.requestFocus();
                }


                Alumno newAlumno = new Alumno(name1, lastname1);
                long id = alumnoController.nuevoEstudiante(newAlumno);

                if (id == -1) {
                    Toast.makeText(AddActivity.this, "Error al guardar . itenta denuevo", Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }

                Toast.makeText(AddActivity.this, "Creado con el id " + id, Toast.LENGTH_LONG).show();

            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void instanciar() {

        etlastname = findViewById(R.id.etLastname);
        etname = findViewById(R.id.etname);
        btnAdd = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);
        alumnoController = new AlumnoController(AddActivity.this);
    }


}

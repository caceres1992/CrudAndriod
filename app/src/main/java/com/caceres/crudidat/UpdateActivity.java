package com.caceres.crudidat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import controllers.AlumnoController;
import model.Alumno;

public class UpdateActivity extends AppCompatActivity {


    private EditText etname, etlastname;
    private Button btnUpdate, btnCancelar;
    private Alumno alumno;
    private AlumnoController alumnoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle extras = getIntent().getExtras();


        if (extras == null) {
            finish();
            return;
        }

        alumnoController = new AlumnoController(UpdateActivity.this);

        long idAlumno = extras.getLong("id");
        String name = extras.getString("name");
        String lastname = extras.getString("lastname");

        alumno = new Alumno(idAlumno, name, lastname);

        etname = findViewById(R.id.etnameUpdate);
        etlastname = findViewById(R.id.etlastnameUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancelar = findViewById(R.id.btnCancel);


        etname.setText(String.valueOf(alumno.getName()));
        etlastname.setText(String.valueOf(alumno.getLastname()));

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etname.setError(null);
                etlastname.setError(null);

                String name1 = etname.getText().toString();
                String lastname2 = etlastname.getText().toString();

                if (name1.isEmpty()){
                    etname.setError("escribe el nombre");
                    etname.requestFocus();
                }
                if (lastname2.isEmpty()){
                    etlastname.setError("escribe el nombre");
                    etlastname.requestFocus();
                }


                Alumno alumnoUpdate = new Alumno(alumno.getId(),name1,lastname2);

                int filasModificadas = alumnoController.guardarCambios(alumnoUpdate);
                if (filasModificadas !=1){
                    Toast.makeText(UpdateActivity.this,"Error al Guardar los cambios",Toast.LENGTH_LONG).show();
                }else{
                    finish();
                }



            }
        });

    }
}

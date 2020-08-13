package com.caceres.crudidat;

import Adapter.AlumnoAdapter;
import Adapter.RecyclerTouch;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import controllers.AlumnoController;
import model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Alumno> listaAlumnos;
    private RecyclerView recyclerView;
    private AlumnoAdapter alumnoAdapter;
    private AlumnoController alumnoController;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alumnoController = new AlumnoController(MainActivity.this);

        instanciar();
        Funciones();


    }

    private void Funciones() {

        listaAlumnos = new ArrayList<>();
        alumnoAdapter = new AlumnoAdapter(listaAlumnos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(alumnoAdapter);

        refrescarLista();


        recyclerView.addOnItemTouchListener(new RecyclerTouch(getApplicationContext(), recyclerView, new RecyclerTouch.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Alumno alumno = listaAlumnos.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id", alumno.getId());
                intent.putExtra("name", alumno.getName());
                intent.putExtra("lastname", alumno.getLastname());
                startActivity(intent);
            }


            //toque largo*/
            @Override
            public void onLongClick(View view, int position) {

                final Alumno alumno = listaAlumnos.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Si,Eliminar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        alumnoController.eliminaralumno(alumno);
                                        refrescarLista();
                                    }
                                }

                        ).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }
                        ).setTitle("Confirmar")
                        .setMessage("Eliminar al Alummo " + alumno.getName() + "?")
                        .create();

                dialog.show();
            }
        }));


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }


    private void instanciar() {
        recyclerView = findViewById(R.id.rvAlumnos);
        btnAdd = findViewById(R.id.btnAdd);
    }


    @Override
    protected void onResume() {
        super.onResume();
        refrescarLista();
    }

    private void refrescarLista() {

        if (alumnoAdapter == null) return;
        listaAlumnos = alumnoController.ListarAlumnos();
        alumnoAdapter.SetAlumno(listaAlumnos);
        alumnoAdapter.notifyDataSetChanged();

    }

}

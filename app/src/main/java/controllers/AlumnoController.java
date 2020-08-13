package controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.ImageSpan;
import config.AlumnoBd;
import model.Alumno;

import java.util.ArrayList;

public class AlumnoController {

    private AlumnoBd alumnoBd;
    private String NOMBRE_TABLA = "tbl_alumno";

    public AlumnoController(Context context) {
        alumnoBd = new AlumnoBd(context);
    }

    public long nuevoEstudiante(Alumno alumno) {

        SQLiteDatabase database = alumnoBd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", alumno.getName());
        values.put("lastname", alumno.getLastname());
        return database.insert(NOMBRE_TABLA, null, values);
    }

    public int guardarCambios(Alumno alumno) {
        SQLiteDatabase database = alumnoBd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", alumno.getName());
        values.put("lastname", alumno.getLastname());
        //where
        String id = "id = ?";

        String[] valoresparaActualizar = {String.valueOf(alumno.getId())};
        return database.update(NOMBRE_TABLA, values, id, valoresparaActualizar);
    }


    public int eliminaralumno(Alumno alumno) {
        SQLiteDatabase database = alumnoBd.getWritableDatabase();
        //where
        String id = "id = ?";
        String[] valores = {String.valueOf(alumno.getId())};
        return database.delete(NOMBRE_TABLA, id, valores);
    }


    public ArrayList<Alumno> ListarAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        SQLiteDatabase database = alumnoBd.getReadableDatabase();
        //SELECT DE LA TABLA
        String[] consulta = {"name", "lastname", "id"};
        Cursor cursor = database.query(
                NOMBRE_TABLA,
                consulta,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            return alumnos;
        }
        if (!cursor.moveToFirst()) return alumnos;
        do {
            String getname = cursor.getString(0);
            String getlastname = cursor.getString(1);
            long getid = cursor.getLong(2);
            Alumno getAlumno = new Alumno(getid, getname, getlastname);
            alumnos.add(getAlumno);
        } while (cursor.moveToNext());
        cursor.close();
        return alumnos;
    }
}

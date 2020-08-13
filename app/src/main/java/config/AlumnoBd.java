package config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class AlumnoBd extends SQLiteOpenHelper {


    private static final String NOMBRE_BASE_DE_DATOS = "alumno";
    private static final String NOMBRE_TABLE_ALUMNO = "tbl_alumno";
    private static final int VERSION_BASE_DE_DATOS = 1;


    public AlumnoBd(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %S(id integer primary key autoincrement,name text,lastname text)",NOMBRE_TABLE_ALUMNO));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

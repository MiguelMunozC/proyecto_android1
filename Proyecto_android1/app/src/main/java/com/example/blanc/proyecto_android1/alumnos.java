package com.example.blanc.proyecto_android1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class alumnos extends AppCompatActivity {

    private EditText et_rut, et_nombre, et_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        et_rut=(EditText)findViewById(R.id.etRut);
        et_nombre=(EditText)findViewById(R.id.etNombre);
        et_telefono=(EditText)findViewById(R.id.etTelefono);
    }

    public void ingresar (View view)
    {
        AdminSqlite admin =
                new AdminSqlite(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();
        String nombre = et_nombre.getText().toString();
        String telefono = et_telefono.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("rut", rut);
        registro.put("nombre", nombre);
        registro.put("telefono", telefono);
        db.insert("alumnos", null, registro);
        db.close();

        et_rut.setText("");
        et_nombre.setText("");
        et_telefono.setText("");

        Toast.makeText(this, "Se guardaron los datos del alumno",
                Toast.LENGTH_LONG).show();
    }

    public void consultarPorRut (View view)
    {
        AdminSqlite admin =
                new AdminSqlite(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();

        Cursor fila = db.rawQuery(
                "select nombre, telefono from alumnos where rut = " + rut, null);

        if (fila.moveToFirst()){
            et_nombre.setText(fila.getString(0));
            et_telefono.setText(fila.getString(1));
        } else {
            Toast.makeText(this, "No existe información del Rut",
                    Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void consultarPorNombre (View view)
    {
        AdminSqlite admin =
                new AdminSqlite(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();

        Cursor fila = db.rawQuery(
                "select rut, telefono from alumnos where nombre = '" + nombre + "'", null);

        if (fila.moveToFirst()){
            et_rut.setText(fila.getString(0));
            et_telefono.setText(fila.getString(1));
        } else {
            Toast.makeText(this, "No existe información del Nombre",
                    Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void eliminarAlumno (View view)
    {
        AdminSqlite admin =
                new AdminSqlite(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();

        int cant = db.delete("alumnos", "rut = '" + rut + "'", null);
        db.close();

        et_rut.setText("");
        et_nombre.setText("");
        et_telefono.setText("");

        if (cant == 1){
            Toast.makeText(this, "Se borró el alumno",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe un alumno con ese Rut",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarAlumno (View view) {
        AdminSqlite admin =
                new AdminSqlite(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();
        String nombre = et_nombre.getText().toString();
        String telefono = et_telefono.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("rut", rut);
        registro.put("nombre", nombre);
        registro.put("telefono", telefono);

        int cant = db.update("alumnos", registro, "rut = " + rut, null);
        db.close();

        if (cant == 1){
            Toast.makeText(this, "Se modificaron los datos del alumno",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe un alumno con ese Rut",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar (View view)
    {
        et_rut.setText("");
        et_nombre.setText("");
        et_telefono.setText("");
    }

    public void volver (View view)
    {
        finish();
    }
}

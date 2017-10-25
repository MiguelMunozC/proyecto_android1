package com.example.blanc.proyecto_android1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class memoriaExterna extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_externa);

        et1 = (EditText)findViewById(R.id.etNombreA);
        et2 = (EditText)findViewById(R.id.etContenido);
    }

    public void grabarArchivo(View view){
        String nombreArchivo = et1.getText().toString();
        String contenidoArchivo = et2.getText().toString();

        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            String ubicacion = tarjeta.getAbsolutePath();

            File file = new File(ubicacion, nombreArchivo);

            OutputStreamWriter myFile = new OutputStreamWriter(new FileOutputStream(file));
            myFile.write(contenidoArchivo);
            myFile.flush();
            myFile.close();

            et1.setText("");
            et2.setText("");
        } catch (Exception e){
            Toast.makeText(this, "No se pudo grabar", Toast.LENGTH_SHORT).show();
        }
    }

    public void recuperarArchivo(View view){
        String nomarchivo = et1.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);

        try {
            FileInputStream fileStream = new FileInputStream(file);
            InputStreamReader archivo =  new InputStreamReader(fileStream);
            BufferedReader br = new BufferedReader(archivo);

            String linea = br.readLine();
            String contenido = "";

            while (linea != null){
                contenido = contenido + linea + "\n";
                linea = br.readLine();
            }

            br.close();
            archivo.close();
            et2.setText(contenido);
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo leer el archivo, o no existe", Toast.LENGTH_LONG).show();
        }
    }

    public  void eliminarArchivo(View view){
        String nombreArchivo = et1.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nombreArchivo);

        if (file.exists()){
            file.delete();
            Toast.makeText(this, "Archivo eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Archivo no existente", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}

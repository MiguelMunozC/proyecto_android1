package com.example.blanc.proyecto_android1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.ForwardingListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class memoria extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        et1 = (EditText)findViewById(R.id.etm_receta);
        String[] archivos = fileList();

        if (existeArchivo(archivos, "receta.txt")){
            try {
                InputStreamReader file = new InputStreamReader(
                        openFileInput("receta.txt"));
                BufferedReader br = new BufferedReader(file);
                String linea = br.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                file.close();
                et1.setText(todo);
            } catch (Exception e) {
                Toast mensaje =
                        Toast.makeText(this, "Error, no fue posible cargar los datos", Toast.LENGTH_LONG);
                mensaje.show();
            }
        }
    }

    public void grabar(View view){
        try {
            OutputStreamWriter file =
                    new OutputStreamWriter(openFileOutput("receta.txt", Activity.MODE_PRIVATE));
            file.write(et1.getText().toString());
            file.flush();
            file.close();

            Toast mensaje = Toast.makeText(this, "Datos grabados", Toast.LENGTH_SHORT);
            mensaje.show();
            finish();
        } catch (Exception e) {
            Toast mensaje =
                    Toast.makeText(this, "Error, no fue posible guardar los datos", Toast.LENGTH_LONG);
            mensaje.show();
        }
    }

    private boolean existeArchivo(String[] archivos, String buscado){
        for (int i = 0; i < archivos.length; i++)
            if (buscado.equals(archivos[i]))
                return true;

            return false;

    }

    public void eliminarArchivo(View view){
        File dir = getFilesDir();
        File file =  new File(dir, "receta.txt");
        file.delete();
        finish();
    }
}

package com.example.blanc.proyecto_android1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irACalculadora(View view){
        Intent i = new Intent(this, SumarRestar.class);
        startActivity(i);
    }
    public void irAListView(View view){
        Intent i = new Intent(this, List_view.class);
        startActivity(i);
    }
    public void irAMemoria(View view){
        Intent i = new Intent(this, memoria.class);
        startActivity(i);
    }
    public void irAMemoriaExterna(View view){
        Intent i = new Intent(this, memoriaExterna.class);
        startActivity(i);
    }

    public void irAAlumnos(View view){
        Intent i = new Intent(this, alumnos.class);
        startActivity(i);
    }
}

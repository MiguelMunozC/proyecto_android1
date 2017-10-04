package com.example.blanc.proyecto_android1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SumarRestar extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar_restar);

        num1 = (EditText)findViewById(R.id.eTNum1);
        num2 = (EditText)findViewById(R.id.eTNum2);
        result = (TextView)findViewById(R.id.tVResultado);
    }

    public void SumarNumeros(View view){
        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        int suma = n1 + n2;

        String resultado = String.valueOf(suma);
        result.setText(resultado);
    }

    public void volver(View view){
        finish();
    }
}

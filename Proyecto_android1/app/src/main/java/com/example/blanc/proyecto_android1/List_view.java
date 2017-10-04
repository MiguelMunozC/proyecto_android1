package com.example.blanc.proyecto_android1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class List_view extends AppCompatActivity {

    private TextView tv_tel;
    private ListView lv_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        tv_tel = (TextView)findViewById(R.id.tvTelefono);
        lv_con = (ListView)findViewById(R.id.lvContactos);

        String[] contactos = {"Juan Torres", "Jorge Espinoza", "Andrés Saez"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, contactos);
        lv_con.setAdapter(adapter);

        final String[] telefonos = {"12345678", "87654321", "54467564"};

        lv_con.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                tv_tel.setText(telefonos[i]);
            }
        });
    }

    public void volver(View view){
        finish();
    }
}

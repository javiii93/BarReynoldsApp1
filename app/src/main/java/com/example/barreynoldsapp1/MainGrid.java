package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.Socket;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;


public class MainGrid extends AppCompatActivity {
    public static String categoria;
    Socket socket=new Socket();
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);
        ListView listViewCategorias = findViewById(R.id.listViewCategorias);

        i = new Intent(this,CategoriasActivity.class);

        MyCustomAdapterMain adaptador= new MyCustomAdapterMain(arrayCategorias,this);
        listViewCategorias.setAdapter(adaptador);

        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // startActivity(i);
            }
        });
    }
}

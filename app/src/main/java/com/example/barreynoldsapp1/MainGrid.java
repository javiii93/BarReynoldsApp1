package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.net.Socket;

import static com.example.barreynoldsapp1.GridCategorias.arrayCategorias;

public class MainGrid extends AppCompatActivity {
    public static String categoria;
    Socket socket=new Socket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);
        ListView listViewCategorias = findViewById(R.id.listViewCategorias);

        arrayCategorias.add(new Categoria("platos calientes papi",22));

        MyCustomAdapterMain adaptador= new MyCustomAdapterMain(arrayCategorias,this);
        listViewCategorias.setAdapter(adaptador);
    }
}

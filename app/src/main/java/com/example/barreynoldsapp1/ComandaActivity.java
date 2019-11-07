package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ComandaActivity extends AppCompatActivity {
    private ArrayList<Comanda> arrayComanda = new ArrayList<>();
    ListView lista1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        lista1 = findViewById(R.id.listView1);
        Producto p = new Producto();
        p.setNombre("fgadfdsf");
        Comanda c = new Comanda(p, 1);
        arrayComanda.add(c);
        // ArrayAdapter<Comanda> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayComanda);
        //  lista1.setAdapter(adaptador);
        MyCustomAdapter adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
        //arrayComanda.add(c);

    }

    public void recuperarObjeto() {
        Intent i = getIntent();
        Producto p = (Producto) i.getSerializableExtra("sampleObject");
        Comanda c = new Comanda(p, 1);
        arrayComanda.add(c);
        Collections.sort(arrayComanda);
        añadirCantidadAlProducto();
    }

    public void añadirCantidadAlProducto() {
        if (arrayComanda.size() > 1) {
            for (int i = 0; i <= arrayComanda.size(); i++) {
                if (arrayComanda.get(i).getProducto().getNombre().equalsIgnoreCase(arrayComanda.get(i + 1).getProducto().getNombre())) {
                    if (arrayComanda.get(i).getCantidad() > arrayComanda.get(i + 1).getCantidad()) {
                        arrayComanda.get(i).setCantidad(arrayComanda.get(i).getCantidad() + 1);
                        arrayComanda.remove(i + 1);
                    } else {
                        arrayComanda.get(i + 1).setCantidad(arrayComanda.get(i + 1).getCantidad() + 1);
                        arrayComanda.remove(i);
                    }
                }
            }
        }
    }

}

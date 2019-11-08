package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ComandaActivity extends AppCompatActivity {
    private ArrayList<Comanda> arrayComanda = new ArrayList<>();
   private ListView lista1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        lista1 = findViewById(R.id.listView1);
        Producto p = new Producto();
        Producto p1 = new Producto();
        p.setNombre("adfdsk");
        p1.setNombre("adfdsf");
        Comanda c = new Comanda(p, 14);
        Comanda c12 = new Comanda(p1, 1);
        arrayComanda.add(c);
        arrayComanda.add(c12);
        MyCustomAdapter adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
        Collections.sort(arrayComanda);
    }

    public void recuperarObjeto() {
        Intent i = getIntent();
        Producto p = (Producto) i.getSerializableExtra("sampleObject");
        Comanda c = new Comanda(p, 1);
        arrayComanda.add(c);
        Collections.sort(arrayComanda);
        añadirCantidadAlProducto();
    }
    public void guardarComanda(){

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

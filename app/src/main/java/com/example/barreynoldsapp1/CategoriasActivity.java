package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CategoriasActivity extends AppCompatActivity {
    private String categoria;
    private ListView lista;
    private ArrayList<Producto> arrayProductos = new ArrayList<>();
    private ArrayAdapter<Producto> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        categoria = getIntent().getStringExtra("categoria");

        // Instanciamos objetos Clase R
        lista = findViewById(R.id.listview);

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayProductos);
        recuperarProductos();
        lista.setAdapter(adaptador);


    }

    public void pasarProductosAComanda(Producto p) {
        Intent i = new Intent(this, ComandaActivity.class);
        i.putExtra("sampleObject", p);
    }

    public void recuperarProductos() {
        try {
            InputStream istream = getAssets().open("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(istream);
            NodeList nList = doc.getElementsByTagName(categoria);
            for (int i = 0; i < nList.getLength(); i++) {
                System.out.println("Estoy dentro del for--------------");
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                Producto p1 = new Producto();
                p1.setNombre(eElement.getElementsByTagName("nom").item(0).getTextContent());
                p1.setPrecio(Float.parseFloat(eElement.getElementsByTagName("preu").item(0).getTextContent()));
                p1.setDescripcion(eElement.getElementsByTagName("descripcio").item(0).getTextContent());
                //p1.setCantidad(Integer.parseInt(eElement.getElementsByTagName("preu").item(0).getTextContent()));
                p1.setCategoria(categoria);
                // p1.setImagen((Image) eElement.getElementsByTagName("imatge"));
                arrayProductos.add(p1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

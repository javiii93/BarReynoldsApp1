package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private int[]flags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        categoria = getIntent().getStringExtra("categoria");

        flags = new int[]{
          R.drawable.aquarius
        };

        // Instanciamos objetos Clase R
        lista = findViewById(R.id.listView);

        recuperarProductos();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayProductos);
        lista.setAdapter(adaptador);

    }

    public void recuperarProductos() {
        try {
            System.out.println(categoria);
            InputStream istream = getAssets().open("productes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(istream);
            NodeList nl =doc.getElementsByTagName(categoria);
            Element e=(Element)nl.item(0);
            NodeList nl2 = e.getChildNodes();

            for (int i = 0; i < nl2.getLength(); i++) {
                if (nl2.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl2.item(i);
                    Producto p1 = new Producto();
                    p1.setNombre(el.getElementsByTagName("nom").item(0).getTextContent());
                    p1.setPrecio(Float.parseFloat(el.getElementsByTagName("preu").item(0).getTextContent()));
                    p1.setDescripcion(el.getElementsByTagName("descripcio").item(0).getTextContent());
                    //p1.setCantidad(Integer.parseInt(el.getElementsByTagName("preu").item(0).getTextContent()));
                    p1.setCategoria(categoria);


                    arrayProductos.add(p1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

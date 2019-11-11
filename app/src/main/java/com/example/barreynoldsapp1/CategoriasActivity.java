package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CategoriasActivity extends AppCompatActivity {
    private String categoria;
    private ListView lista;
    private ArrayList<Producto> arrayProductos = new ArrayList<>();
    private ArrayList<Producto> arrayComanda2=new ArrayList<>();
    private MyCustomAdapter2 adaptador;
    //private ArrayAdapter<Producto> adaptador;
    private String imgUri;
    Intent i;
    Resources resources;
    XmlResourceParser xmlParser;
    Document doc;
    Producto p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        i = new Intent(this, ComandaActivity.class);
        categoria = getIntent().getStringExtra("categoria");
        resources = getResources();
        // Instanciamos objetos Clase R
        lista = findViewById(R.id.listView);
        recuperarProductos();
        //adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayProductos);
        adaptador = new MyCustomAdapter2(arrayProductos, this);
        lista.setAdapter(adaptador);
       /* Producto pp=new Producto();
        pp.setNombre("fsdafdas");
        pasarProductosAComanda(pp);*/
       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               arrayComanda2.add((Producto)parent.getItemAtPosition(position));
           }
       });

    }

    public void pasarProductosAComanda(ArrayList<Producto> p) {
        i.putExtra("sampleObject", p);
      //  p.setImagen(0);
       // startActivity(i);
    }

    public void recuperarProductos() {
        try {
            System.out.println(categoria);
            InputStream istream = getAssets().open("productes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(istream);
            // NodeList de la categoria
            NodeList nl = doc.getElementsByTagName(categoria);
            // Element del primer elemento del NodeList
            Element e = (Element) nl.item(0);
            // NodeList de los hijos del elemento
            NodeList nl2 = e.getChildNodes();

            for (int i = 0; i < nl2.getLength(); i++) {
                if (nl2.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl2.item(i);
                    p1 = new Producto();
                    p1.setNombre(el.getElementsByTagName("nom").item(0).getTextContent());
                    p1.setPrecio(Float.parseFloat(el.getElementsByTagName("preu").item(0).getTextContent()));
                    p1.setDescripcion(el.getElementsByTagName("descripcio").item(0).getTextContent());
                    p1.setCategoria(categoria);
                    //p1.setCantidad(Integer.parseInt(el.getElementsByTagName("").item(0).getTextContent()));
                    String ruta = acortarRuta(el.getElementsByTagName("image").item(0).getAttributes().item(0).getNodeValue());
                    int drawableResourceId = getResources().getIdentifier(ruta, "drawable", getPackageName());
                    p1.setImagen(drawableResourceId);
                    arrayProductos.add(p1);

                }
            }

            // Rellenar imagenes
            //queryXpath(categoria);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void onClick(View view) {
        pasarProductosAComanda(arrayComanda2);
        startActivity(i);
    }

    public String acortarRuta(String s) {
        String result;
        for (int i = s.length() - 1; i > 0; i--) {

            if (s.charAt(i) == '/') {
                result = s.substring(i + 1, s.length() - 4);
                return result;
            }
        }
        return null;
    }
}

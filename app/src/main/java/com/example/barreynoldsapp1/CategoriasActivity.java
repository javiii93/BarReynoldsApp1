package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class CategoriasActivity extends AppCompatActivity {
    private String categoria;
    private ListView lista;
    private ArrayList<Producto> arrayProductos = new ArrayList<>();
    private ArrayAdapter<Producto> adaptador;
    private String imgUri;
    Resources resources;
    XmlResourceParser xmlParser;
    Document doc;
    Producto p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        categoria = getIntent().getStringExtra("categoria");
        resources = getResources();


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
                    //p1.setCantidad(Integer.parseInt(el.getElementsByTagName("preu").item(0).getTextContent()));
                    arrayProductos.add(p1);

                }
            }
            // Rellenar imagenes
            queryXpath(categoria);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void queryXpath(String categoria) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xPath.evaluate("//"+categoria+"/producte/imatge/image/@href", doc, XPathConstants.NODESET);

        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            try {
                // get input stream
                InputStream ims = getAssets().open(node.getTextContent());
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                //p1.setImagen();
                System.out.println(node.getTextContent());
            }
            catch(IOException ex) {
                return;
            }
        }
    }
}

package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ComandaActivity extends AppCompatActivity {
    public static  ArrayList<Comanda> arrayComanda = new ArrayList<>();
   private ListView lista1;
   TextView tvv;
    Button deleteBtn,addBtn;
    Document doc;
    Element comanda,producte,nom,quantitat,preu;
    File comandaFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        lista1 = findViewById(R.id.listView1);
        comandaFile=new File(getFilesDir(),"comanda.xml");

        recuperarObjeto();

        MyCustomAdapter adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
        Collections.sort(arrayComanda);
    }

    public void recuperarObjeto() {
        Comanda c;
        ArrayList<Producto> p = (ArrayList<Producto>) getIntent().getSerializableExtra("sampleObject");
        for(int i=0;i<p.size();i++){
            c = new Comanda(p.get(i), 1,p.get(i).getImagen());
            arrayComanda.add(c);
            Collections.sort(arrayComanda);
            añadirCantidadAlProducto();
        }
    }
    public void guardarComanda() throws TransformerException, IOException {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
             comanda = doc.createElement("comanda");
            // comanda = doc.getDocumentElement();
             nom = doc.createElement("nom");
             preu = doc.createElement("preu");
             quantitat = doc.createElement("quantitat");


        }  catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        for(int i=0;i<arrayComanda.size();i++) {
            producte = doc.createElement("producte");
            nom.appendChild(doc.createTextNode(arrayComanda.get(i).getProducto().getNombre()));
            preu.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getPrecio())));
            quantitat.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getCantidad())));
            producte.appendChild(nom);
            producte.appendChild(preu);
            producte.appendChild(quantitat);

            comanda.appendChild(producte);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(comandaFile);

            transformer.transform(source, result);
        }
        doc.appendChild(comanda);
    }

    public void añadirCantidadAlProducto() {
        if (arrayComanda.size() > 1) {
            for (int i = 0; i <= arrayComanda.size(); i++) {
                try {
                    if (arrayComanda.get(i).getProducto().getNombre().equalsIgnoreCase(arrayComanda.get(i + 1).getProducto().getNombre())) {
                        if (arrayComanda.get(i).getCantidad() > arrayComanda.get(i + 1).getCantidad()) {
                            //arrayComanda.get(i).setCantidad(arrayComanda.get(i).getCantidad() + 1);
                            arrayComanda.remove(i + 1);
                        } else {
                            //arrayComanda.get(i + 1).setCantidad(arrayComanda.get(i + 1).getCantidad() + 1);
                            arrayComanda.remove(i);
                        }
                    }
                }catch (Exception e){

                }
            }
        }
    }

    public void onBackPressed(){
        Log.d("-------","apreto boton atras");
        try {
            Log.d("---....----",arrayComanda.toString());
            guardarComanda();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();

    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

}

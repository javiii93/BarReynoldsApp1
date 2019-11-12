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
    private  ArrayList<Comanda> arrayComanda = new ArrayList<>();
   private ListView lista1;
    Button deleteBtn;
    Document doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        lista1 = findViewById(R.id.listView1);
        recuperarObjeto();

        MyCustomAdapter adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
        Collections.sort(arrayComanda);

        deleteBtn =(Button)findViewById(R.id.delete_btn);
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



        }  catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        for(int i=0;i<arrayComanda.size();i++) {
            Element comanda = doc.createElement("comanda");

            Element producte = doc.createElement("producte");

            Element nom = doc.createElement("nom");
            Element preu = doc.createElement("preu");
            Element quantitat = doc.createElement("quantitat");

            nom.appendChild(doc.createTextNode(arrayComanda.get(i).getProducto().getNombre()));
            preu.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getPrecio())));
            quantitat.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getCantidad())));
            producte.appendChild(nom);
            producte.appendChild(preu);
            producte.appendChild(quantitat);

            comanda.appendChild(producte);
            doc.appendChild(comanda);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(getFilesDir(),"comanda.xml"));
            transformer.transform(source, result);



            //Log.d("---",producte.getElementsByTagName("nom").item(0).getNodeValue());


        }
    }

    public void añadirCantidadAlProducto() {
        if (arrayComanda.size() > 1) {
            for (int i = 0; i <= arrayComanda.size(); i++) {
                try {
                    if (arrayComanda.get(i).getProducto().getNombre().equalsIgnoreCase(arrayComanda.get(i + 1).getProducto().getNombre())) {
                        if (arrayComanda.get(i).getCantidad() > arrayComanda.get(i + 1).getCantidad()) {
                            arrayComanda.get(i).setCantidad(arrayComanda.get(i).getCantidad() + 1);
                            arrayComanda.remove(i + 1);
                        } else {
                            arrayComanda.get(i + 1).setCantidad(arrayComanda.get(i + 1).getCantidad() + 1);
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
            guardarComanda();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();

    }


}

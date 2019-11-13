package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.barreynoldsapp1.ComandaActivity.arrayComanda;

public class MainActivity extends AppCompatActivity {
    Button b;
    Document doc;
    Element comanda,producte,nom,quantitat,preu;
    File comandaFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comandaFile=new File(getFilesDir(),"comanda.xml");

    }

    public void onClick(View view) {
        Intent i = new Intent(this, CategoriasActivity.class);
        b = new Button(view.getContext());
        i.putExtra("categoria",((ImageButton)view).getContentDescription().toString());
        startActivity(i);
    }
    public void onClickFinal(View view) throws TransformerException {
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
}
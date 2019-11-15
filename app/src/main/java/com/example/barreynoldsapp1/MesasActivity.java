package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MesasActivity extends AppCompatActivity {
    Document doc;
    int numeroMesas;
    ListView listViewMesas;
    ArrayList<String>arrayMesas=new ArrayList<>();
    ArrayList<Button>arrayBotones=new ArrayList<>();
    Intent intent;
    private MyCustomAdapterMesas adaptador;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        listViewMesas=findViewById(R.id.listViewMesas);
        b1=findViewById(R.id.buttonMesa);

        listViewMesas.setDivider(null);
        listViewMesas.setDividerHeight(0);
        intent=new Intent(this,MainActivity.class);
        try {
            numeroMesas=recuperarMesas();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for(int i=1;i<=numeroMesas;i++){
            arrayMesas.add("Mesa "+i);
           // arrayBotones.add(new Button(this,0,));
        }
        adaptador= new MyCustomAdapterMesas(arrayMesas,this);
        listViewMesas.setAdapter(adaptador);
        listViewMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(intent);
            }
        });


    }
    public Integer recuperarMesas() throws IOException, ParserConfigurationException, SAXException {
        InputStream istream = getAssets().open("config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(istream);
        NodeList nl = doc.getElementsByTagName("numeroTaulas");
        Element e1=(Element)nl.item(0);
        return Integer.valueOf(e1.getTextContent());
    }
}

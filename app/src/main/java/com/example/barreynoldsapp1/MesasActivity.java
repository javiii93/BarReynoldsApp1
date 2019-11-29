package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;
import static com.example.barreynoldsapp1.Camareros_Activity.numMesas;

public class MesasActivity extends AppCompatActivity {
    Document doc;
    ListView listViewMesas;
    ArrayList<Button>arrayMesas=new ArrayList<>();
    Intent intent;
    Button b1;
    MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        sonido = MediaPlayer.create(this, R.raw.bambu);
        listViewMesas=findViewById(R.id.listViewMesas);
        b1=findViewById(R.id.buttonMesa);
        listViewMesas.setDivider(null);
        listViewMesas.setDividerHeight(0);
        intent=new Intent(this,MainActivity.class);
        //intent=new Intent(this,MainGrid.class);
        //intent=new Intent(this,GridCategorias.class);

        listViewMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                sonido.start();
                intent.putExtra("mesaNum",v.getContentDescription());
                startActivity(intent);
            }
        });
        // Prueba local
       // numMesas=5;
        for(int i=0;i<numMesas;i++){
            arrayMesas.add(b1);
        }

        MyCustomAdapterMesas adaptador= new MyCustomAdapterMesas(arrayMesas,this);
        listViewMesas.setAdapter(adaptador);



    }

    @Override
    public void onBackPressed() {
        sonido.start();
        super.onBackPressed();
        intent = new Intent(getApplicationContext(),Camareros_Activity.class);
        arrayCategorias=new ArrayList<Categoria>();
        startActivity(intent);
    }
}

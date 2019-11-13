package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

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
    public ListView lista1;
   TextView tvv;
    Button deleteBtn,addBtn;
    Document doc;
    Comanda c = new Comanda();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        // Recoge los productos de CategoriasActivity los añade a arrayComanda
        recuperarObjeto();
        lista1 = findViewById(R.id.listView1);
        MyCustomAdapter adaptador = new MyCustomAdapter(arrayProductos2, this);
        lista1.setAdapter(adaptador);

    }
    public void recuperarObjeto() {
        ArrayList<Producto> p = (ArrayList<Producto>) getIntent().getSerializableExtra("sampleObject");
        Collections.sort(p);

        for(int i=0;i<p.size();i++){
            c.setProducto(p.get(i));
            c.setCantidad(p.get(i).getCantidad());
            c.setImagen(p.get(i).getImagen());

            Collections.sort(arrayProductos2);
        }
    }
}

package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

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
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ComandaActivity extends AppCompatActivity {
    private ArrayList<Comanda> arrayComanda = new ArrayList<>();
    private ListView lista1;
    Button deleteBtn;
    Intent i;
    MyCustomAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        i = new Intent(this, CategoriasActivity.class);
        lista1 = findViewById(R.id.listView1);
        recuperarObjeto();

        deleteBtn = (Button) findViewById(R.id.delete_btn);

        adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
        //arrayComanda= adaptador.getList();
        Collections.sort(arrayComanda);
        System.out.println("entro");
    }


    public void onBackPressed() {
      //  guardarComanda();
      /*  System.out.println(adaptador.getCount());
        adaptador.setList(arrayComanda);
        //arrayComanda.clear();
        for(int j=0;j<adaptador.getCount();j++){
          //  arrayComanda.get(j).setCantidad(adaptador.getList().get(j).getCantidad());
          //  arrayComanda.add((Comanda)adaptador.getItem(j));
        }*/
        // arrayComanda=adaptador.getList();
        //i.putExtra("sampleObject2",enviarArrayProductosActualizada(adaptador.getList()));
        super.onBackPressed();
    }

    public ArrayList<Producto> enviarArrayProductosActualizada(ArrayList<Comanda> ac) {
        ArrayList<Producto> ap = new ArrayList<>();
        for (int j = 0; j < ac.size(); j++) {
            ap.add(ac.get(j).getProducto());
        }
        return ap;
    }

    public void recuperarObjeto() {
        Comanda c;
        ArrayList<Producto> p = (ArrayList<Producto>) getIntent().getSerializableExtra("sampleObject");
        for (int i = 0; i < p.size(); i++) {

            c = new Comanda(p.get(i), 1, p.get(i).getImagen());
            arrayComanda.add(c);
            Collections.sort(arrayComanda);
            añadirCantidadAlProducto();

        }
    }

  /*  public void guardarComanda() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(istream);
            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("comanda");
            doc.appendChild(eRaiz);
            for (int i = 0; i < arrayComanda.size(); i++) {
                // definimos el nodo que contendrá los elementos
                Element eProducto = doc.createElement("producto");
                eRaiz.appendChild(eProducto);
                // atributo para el nodo jugador
                Attr attr = doc.createAttribute("Numero");
                attr.setValue(String.valueOf(i + 1));
                eProducto.setAttributeNode(attr);
                // definimos cada uno de los elementos y le asignamos un valor
                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(arrayComanda.get(i).getProducto().getNombre()));
                eProducto.appendChild(eNombre);
                Element eCantidad = doc.createElement("cantidad");
                eCantidad.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getCantidad())));
                eProducto.appendChild(eCantidad);
            }
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
           // StreamResult result = new StreamResult(file);
            transformer.transform(source, result);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    public void recuperarComanda() {

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
                } catch (Exception e) {

                }
            }
        }
    }


}

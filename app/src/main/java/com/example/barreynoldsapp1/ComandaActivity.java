package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.barreynoldsapp1.Camareros_Activity.nombreEmpleado;
import static com.example.barreynoldsapp1.Camareros_Activity.numMesas;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.barreynoldsapp1.CategoriasActivity.created;
import static com.example.barreynoldsapp1.MyCustomAdapter.imprimirProductos;


public class ComandaActivity extends AppCompatActivity {
    public static  ArrayList<Comanda> arrayComanda = new ArrayList<>();
    public ListView lista1;
    TextView tvv;
    Button deleteBtn,addBtn;
    Document doc;
    private File file;
    private String rutaComandaXml="comanda.xml";
    String nuevaComanda;
    Comanda c = new Comanda();
    int selectedRow;
    final MyCustomAdapter adaptador = new MyCustomAdapter(this);


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        Collections.sort(arrayProductos2);
        deleteBtn=(Button)findViewById(R.id.delete_btn);
        addBtn=(Button)findViewById(R.id.botonCategoria);
        lista1 = findViewById(R.id.listView1);


        lista1.setAdapter(adaptador);

        // Muestra array de productos por consola
        imprimirProductos();
        // Recoge los productos de CategoriasActivity los añade a arrayComanda
        Collections.sort(arrayProductos2);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeListItem(view,i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        created=false;
        super.onBackPressed();
    }

    protected void removeListItem(View rowView, final int positon) {

        final Animation animation = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        rowView.startAnimation(animation);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                arrayProductos2.remove(positon);
                adaptador.notifyDataSetChanged();
                animation.cancel();
            }
        },1000);

    }
    public void guardarComandaFinalizado(View view) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            nuevaComanda=rutaComandaXml.substring(0,rutaComandaXml.length()-4)+numMesas+".xml";
            file=new File(getFilesDir(),nuevaComanda);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("comanda");
            doc.appendChild(eRaiz);
            for (int i = 0; i < arrayProductos2.size(); i++) {
                // definimos el nodo que contendrá los elementos
                Element eProducto = doc.createElement("producto");
                eRaiz.appendChild(eProducto);
                // atributo para el nodo jugador
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(arrayProductos2.get(i).getId()));
                eProducto.setAttributeNode(attr);
                // definimos cada uno de los elementos y le asignamos un valor
                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(arrayProductos2.get(i).getNombre()));
                eProducto.appendChild(eNombre);

                Element ePrecio = doc.createElement("precio");
                ePrecio.appendChild(doc.createTextNode(String.valueOf(arrayProductos2.get(i).getPrecio())));
                eProducto.appendChild(ePrecio);

                Element eDescripcion = doc.createElement("descripcion");
                eDescripcion.appendChild(doc.createTextNode(arrayProductos2.get(i).getDescripcion()));
                eProducto.appendChild(eDescripcion);

                Element eImagen = doc.createElement("imagen");
                eImagen.appendChild(doc.createTextNode(String.valueOf(arrayProductos2.get(i).getImagen())));
                eProducto.appendChild(eImagen);

                Element eCantidad = doc.createElement("cantidad");
                eCantidad.appendChild(doc.createTextNode(String.valueOf(arrayProductos2.get(i).getCantidad())));
                eProducto.appendChild(eCantidad);
            }
            Element eCamarero = doc.createElement("camarero");
            eCamarero.appendChild(doc.createTextNode(nombreEmpleado));
            eRaiz.appendChild(eCamarero);
           /* Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(e.getId()));
            eCamarero.setAttributeNode(attr);*/

            Element eMesa = doc.createElement("mesa");
            eMesa.appendChild(doc.createTextNode(String.valueOf(numMesas)));
            eRaiz.appendChild(eMesa);

            Element eFechaHora = doc.createElement("fecha");
            eFechaHora.appendChild(doc.createTextNode(hourdateFormat.format(date)));
            eRaiz.appendChild(eFechaHora);

            Element eFin = doc.createElement("fin");
            eFin.appendChild(doc.createTextNode("2"));
            eRaiz.appendChild(eFin);

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
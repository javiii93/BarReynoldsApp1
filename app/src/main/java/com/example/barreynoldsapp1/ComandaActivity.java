package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
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
    private File file;
    private String rutaComandaXml="comanda.xml";
    private MyCustomAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        file=new File(getFilesDir(), rutaComandaXml);
        lista1 = findViewById(R.id.listView1);
        if (file.exists()){
            recuperarComanda();
        }
        recuperarObjeto();
        Collections.sort(arrayComanda);
        adaptador = new MyCustomAdapter(arrayComanda, this);
        lista1.setAdapter(adaptador);
    }


    public void onBackPressed() {
       guardarComanda();
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
            //System.out.println(c.getProducto().getNombre());
        }
        Collections.sort(arrayComanda);

        for(int i=0;i<arrayComanda.size();i++){
            System.out.println(  arrayComanda.get(i).getProducto().getNombre());
            System.out.println( arrayComanda.get(i).getCantidad());
        }

        añadirCantidadAlProducto();
    }

    public void guardarComanda() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("comanda");
            doc.appendChild(eRaiz);
            for (int i = 0; i < arrayComanda.size(); i++) {
                // definimos el nodo que contendrá los elementos
                Element eProducto = doc.createElement("producto");
                eRaiz.appendChild(eProducto);
                // atributo para el nodo jugador
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(arrayComanda.get(i).getProducto().getId()));
                eProducto.setAttributeNode(attr);
                // definimos cada uno de los elementos y le asignamos un valor
                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(arrayComanda.get(i).getProducto().getNombre()));
                eProducto.appendChild(eNombre);

                Element ePrecio = doc.createElement("precio");
                ePrecio.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getPrecio())));
                eProducto.appendChild(ePrecio);

                Element eDescripcion = doc.createElement("descripcion");
                eDescripcion.appendChild(doc.createTextNode(arrayComanda.get(i).getProducto().getDescripcion()));
                eProducto.appendChild(eDescripcion);

                Element eImagen = doc.createElement("imagen");
                eImagen.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getProducto().getImagen())));
                eProducto.appendChild(eImagen);

                Element eCantidad = doc.createElement("cantidad");
                eCantidad.appendChild(doc.createTextNode(String.valueOf(arrayComanda.get(i).getCantidad())));
                eProducto.appendChild(eCantidad);
            }
            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recuperarComanda() {
        Producto p1=new Producto();
        Comanda c1=new Comanda();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList nList = doc.getElementsByTagName("producto");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    p1.setNombre(eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    p1.setPrecio(Float.parseFloat(eElement.getElementsByTagName("precio").item(0).getTextContent()));
                    p1.setDescripcion(eElement.getElementsByTagName("descripcion").item(0).getTextContent());
                    p1.setImagen(Integer.parseInt(eElement.getElementsByTagName("imagen").item(0).getTextContent()));
                    c1.setProducto(p1);
                    c1.setCantidad(Integer.parseInt(eElement.getElementsByTagName("cantidad").item(0).getTextContent()));
              arrayComanda.add(c1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void añadirCantidadAlProducto() {
        if (arrayComanda.size() > 1) {
            try{
            for (int i = 0; i+1 <= arrayComanda.size(); i++) {
                    if (arrayComanda.get(i).getProducto().getNombre().equalsIgnoreCase(arrayComanda.get(i + 1).getProducto().getNombre())) {
                        if (arrayComanda.get(i).getCantidad() > arrayComanda.get(i + 1).getCantidad()) {
                            arrayComanda.get(i).setCantidad(arrayComanda.get(i).getCantidad() + 1);
                            arrayComanda.remove(i + 1);
                        } else {
                            arrayComanda.get(i + 1).setCantidad(arrayComanda.get(i + 1).getCantidad() + 1);
                            arrayComanda.remove(i);
                        }
                    }
            }}catch (Exception e){

            }
        }
    }


}

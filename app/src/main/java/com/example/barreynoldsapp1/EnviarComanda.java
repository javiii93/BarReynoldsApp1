package com.example.barreynoldsapp1;

import android.os.StrictMode;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.barreynoldsapp1.Camareros_Activity.nombreEmpleado;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

public class EnviarComanda {
    String nuevaComanda;
    String mesaNum;
    private File file;
    private String rutaComandaXml="comanda.xml";

    public void finalizar(){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            nuevaComanda=rutaComandaXml.substring(0,rutaComandaXml.length()-4)+mesaNum+".xml";
            //file=new File(getFilesDir(),nuevaComanda);
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
                eImagen.appendChild(doc.createTextNode(String.valueOf(arrayProductos2.get(i).getFoto())));
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
            eMesa.appendChild(doc.createTextNode(mesaNum));
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
    public void enviar(){

    }


}

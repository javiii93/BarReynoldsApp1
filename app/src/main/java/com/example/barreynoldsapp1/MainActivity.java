package com.example.barreynoldsapp1;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;
import static com.example.barreynoldsapp1.ComandaActivity.arrayComanda;

public class MainActivity extends AppCompatActivity {
    Button b;
    Socket socket=new Socket();
    private File file;
    private String rutaComandaXml="comanda.xml";
    String nuevaComanda;
    public static String host = "192.168.40.44";
    public static int port = 4444;
    String mesaNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesaNum=getIntent().getExtras().getString("mesaNum");
        Log.d("------",mesaNum);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, CategoriasActivity.class);
        b = new Button(view.getContext());
        i.putExtra("categoria",((ImageButton)view).getContentDescription().toString());
        startActivity(i);
    }
    public void onClickFinal(View view) {
        guardarComanda();
    }
    public void guardarComanda() {
        try {
            nuevaComanda=rutaComandaXml.substring(0,rutaComandaXml.length()-4)+mesaNum+".xml";
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
            // Conexion con el servidor
            host = "192.168.40.44";

            // CONEXION SOCKET IP CON TIMEOUT POR SI NO PUEDE CONECTAR CON EL HOST
            try{
                InetSocketAddress sockAdr = new InetSocketAddress(host, port);
                socket = new Socket();
                int timeout = 5000;
                socket.connect(sockAdr, timeout);
                if(socket.isConnected()) {
                    File file = new File(getFilesDir(), rutaComandaXml);
                    // Get the size of the file
                    byte[] bytes = new byte[16 * 1024];
                    InputStream in = new FileInputStream(file);
                    OutputStream out = socket.getOutputStream();

                    int count;
                    while ((count = in.read(bytes)) > 0) {
                        out.write(bytes, 0, count);
                    }
                    out.close();
                    in.close();
                    socket.close();
                }


            }catch (SocketTimeoutException e){
                Toast.makeText(this,"No se pudo conectar con el servidor",Toast.LENGTH_LONG).show();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d("Socket","Socket Closed");
                }
            }



        }

        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    public void onBackPressed(){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Seguro que quieres finalizar la comanda de la mesa "+mesaNum);
            alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    guardarComanda();
                    if(socket.isConnected()){
                        startActivity(new Intent(getApplicationContext(),MesasActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"No se pudo conectar con el servidor",Toast.LENGTH_LONG);
                    }
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.show();


    }

}
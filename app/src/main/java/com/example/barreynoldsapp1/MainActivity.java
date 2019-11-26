package com.example.barreynoldsapp1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;
import static com.example.barreynoldsapp1.Camareros_Activity.arrayTotalProductos;
import static com.example.barreynoldsapp1.Camareros_Activity.nombreEmpleado;
import static com.example.barreynoldsapp1.Camareros_Activity.host;
import static com.example.barreynoldsapp1.Camareros_Activity.numMesas;
import static com.example.barreynoldsapp1.Camareros_Activity.port;
import static com.example.barreynoldsapp1.Camareros_Activity.timeout;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;


public class MainActivity extends AppCompatActivity {
    Button b;
    Socket socket=new Socket();
    private File file;
    private String rutaComandaXml="comanda.xml";
    String nuevaComanda;
    ObjectInputStream in;
    ObjectOutputStream out;
    String uri;
    public static String mesaNum;
    public static String categoria;
    //ListView listViewCategorias;
    GridView gridFiends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        gridFiends=findViewById(R.id.gridFriends);

        mesaNum=getIntent().getStringExtra("mesaNum");
        //listViewCategorias=findViewById(R.id.listViewCategorias);
        //listViewCategorias.setDivider(null);
        //listViewCategorias.setDividerHeight(0);
        //nombreEmpleado=getIntent().getExtras().toString();
        System.out.println("-------"+nombreEmpleado);
        //CrearConexion.conexion();
        for(int i=0;i<arrayCategorias.size();i++) {
            Log.d("....", arrayCategorias.get(i).getNombre());
        }

        MyCustomAdapterMain adaptador= new MyCustomAdapterMain(arrayCategorias,this);
        //listViewCategorias.setAdapter(adaptador);
        gridFiends.setAdapter(adaptador);
    }

    public void onClick(View view) {
        b = new Button(view.getContext());
        categoria=view.getContentDescription().toString();
        buttonEffect(b);
        Intent i = new Intent(this, CategoriasActivity.class);
        startActivity(i);
    }

    public void conexionServidor() {
        // CONEXION SOCKET IP CON TIMEOUT POR SI NO PUEDE CONECTAR CON EL HOST
        try{
            port = 4444;
            InetSocketAddress sockAdr = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sockAdr, timeout);
            if(socket.isConnected()) {
                File file = new File(getFilesDir(), nuevaComanda);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void guardarComandaInacabada() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
            Element eCamarero = doc.createElement("camarero");
            eCamarero.appendChild(doc.createTextNode(nombreEmpleado));
            eRaiz.appendChild(eCamarero);
         /*   Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(e.getId()));
            eCamarero.setAttributeNode(attr);*/

            Element eMesa = doc.createElement("mesa");
            eMesa.appendChild(doc.createTextNode(String.valueOf(mesaNum)));
            eRaiz.appendChild(eMesa);

            Element eFechaHora = doc.createElement("fecha");
            eFechaHora.appendChild(doc.createTextNode(hourdateFormat.format(date)));
            eRaiz.appendChild(eFechaHora);

            Element eFin = doc.createElement("fin");
            eFin.appendChild(doc.createTextNode("1"));
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
    public static void buttonEffect(View view){
        view.setOnTouchListener(new View.OnTouchListener() {

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
            alert.setMessage("Quieres finalizar la comanda de la mesa "+mesaNum);
            alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    arrayProductos2=new ArrayList<>();
                    guardarComandaInacabada();
                    startActivity(new Intent(getApplicationContext(),MesasActivity.class));
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
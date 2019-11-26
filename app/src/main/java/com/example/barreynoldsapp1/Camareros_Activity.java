package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Camareros_Activity extends AppCompatActivity implements Serializable {
    private ArrayList<Cambrer> arrayCamareros = new ArrayList<>();
    private ListView lista;
    private Intent i;
    public static int timeout=5000;
    public static String host = "";
    public static int port = 4445;
    String uri;
    public static int numMesas;
    public static ArrayList<Producto> arrayTotalProductos=new ArrayList<Producto>();
    public static ArrayList<String>categorias;
    public static ArrayList<Categoria> arrayCategorias=new ArrayList<>();
    public static Socket socket=new Socket();
    public static InetSocketAddress sockAdr;
    private String rutaComandaXml="camareros.xml";
    public static String nombreEmpleado;
    Document doc;
    ObjectInputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camareros);
        try{
            getConfig();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            // *--------------------------*
            // uso local / frente server
            //arrayCamareros.add(new Cambrer(1,"Pepe","password"));
            //arrayCategorias.add(new Categoria("platos calientes papi",22,drawableResourceId));

            conexionServidor();
            // *--------------------------*
            uri = "bebidalol_1.png";
            int drawableResourceId = this.getResources().getIdentifier(uri, "drawable", this.getPackageName());
            for (int i=0;i<categorias.size();i++){
                arrayCategorias.add(new Categoria(categorias.get(i),11,drawableResourceId));
            }
            i=new Intent(this,MesasActivity.class);

            CustomAdapterEmpleados adaptador = new CustomAdapterEmpleados(arrayCamareros, this);
            lista = findViewById(R.id.listview5);
            lista.setAdapter(adaptador);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    nombreEmpleado=parent.getItemAtPosition(position).toString();
                    //nombreEmpleado.toLowerCase().replaceAll("[\\d]","");
                    Log.d("camarero seleccionado",nombreEmpleado);
                    //i.putExtra("camarero",nombreEmpleado);
                    startActivity(i);
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void conexionServidor(){
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // CONEXION SOCKET IP CON TIMEOUT POR SI NO PUEDE CONECTAR CON EL HOST
        try{
            sockAdr = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sockAdr, timeout);
            if(socket.isConnected()) {
                in = new ObjectInputStream(socket.getInputStream());
                try {
                    Object ob = in.readObject();
                    arrayCamareros = (ArrayList<Cambrer>)ob;
                    System.out.println("--- "+arrayCamareros.toString());
                    numMesas=(int) in.readObject();
                    categorias=(ArrayList<String>)in.readObject();
                    arrayTotalProductos=(ArrayList<Producto>)in.readObject();
                    System.out.println("--- "+arrayTotalProductos.toString());

                    System.out.println(categorias.toString());

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                in.close();
                socket.close();
            }
        }catch (SocketTimeoutException e){
            Toast.makeText(this,"No se pudo conectar con el servidor",Toast.LENGTH_LONG).show();
            /*Intent intent = new Intent(this,PrincipalActivity.class);
            finish();
            startActivity(intent);*/
            try {

                socket.close();
                InetSocketAddress sockAdr = new InetSocketAddress(host, port);
                socket = new Socket();

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

    public void getConfig() throws IOException, ParserConfigurationException, SAXException {
        InputStream istream = getAssets().open("config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(istream);
        // NodeList de la categoria
        NodeList nl = doc.getElementsByTagName("ipServer");
        // Element del primer elemento del NodeList
        Element e2=(Element)nl.item(0);
        host=e2.getTextContent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

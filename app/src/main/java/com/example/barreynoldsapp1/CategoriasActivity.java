package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.media.MediaPlayer;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayTotalProductos;
import static com.example.barreynoldsapp1.Camareros_Activity.host;
import static com.example.barreynoldsapp1.Camareros_Activity.port;
import static com.example.barreynoldsapp1.Camareros_Activity.sockAdr;
import static com.example.barreynoldsapp1.Camareros_Activity.timeout;
import static com.example.barreynoldsapp1.MainActivity.categoria;


public class CategoriasActivity extends AppCompatActivity {
    private ListView lista;
    //Contiene productos del XML
    private ArrayList<Producto> arrayProductos = new ArrayList<>();
    //Contiene productos clickados
    public static ArrayList<Producto> arrayProductos2=new ArrayList<>();
    private ArrayList<Producto> arrayProductos3 = new ArrayList<>();

    private MyCustomAdapter2 adaptador;
    private String imgUri;
    Intent i;
    Resources resources;
    XmlResourceParser xmlParser;
    Document doc;
    Producto p1;
    boolean cambiado=false;
    static boolean created;
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket socket=new Socket();
    int identifier;
    String nombreFoto;
    MediaPlayer sonido,sonido2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        created=true;
        sonido = MediaPlayer.create(this, R.raw.bambu);
        sonido2 = MediaPlayer.create(this, R.raw.roblox);
        sonido2.start();

        Collections.sort(arrayProductos2);
        i = new Intent(this, ComandaActivity.class);
        resources = getResources();
        // Instanciamos objetos Clase R
        lista = findViewById(R.id.listView);
        //Lee XML e introduce productos en arrayProductos para mostrarlos
        //recuperarProductosXML();

        for(Producto p:arrayTotalProductos){
            if(p.getCategoriaNombre().contains(categoria)){
                nombreFoto=p.getNombre().toLowerCase().replaceAll(" ","_").replaceAll("-","_").replaceAll("ç","c");
                identifier=getResources().getIdentifier(nombreFoto,"drawable",getPackageName());
                p.setImagen(identifier);
                arrayProductos3.add(p);
            }
        }

        adaptador = new MyCustomAdapter2(arrayProductos3, this);
        adaptador.notifyDataSetChanged();

        lista.setAdapter(adaptador);
       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Producto p=((Producto)parent.getItemAtPosition(position));
               sonido.start();
               if(arrayProductos2.isEmpty()){
                   p.setCantidad(1);
                   arrayProductos2.add(p);
               }
               else {
                   for (int i = 0; i < arrayProductos2.size(); i++) {
                       if (arrayProductos2.get(i).getNombre().equals(p.getNombre())) {
                           arrayProductos2.get(i).setCantidad(arrayProductos2.get(i).getCantidad() + 1);
                           cambiado=true;
                       }
                   }
                   if(cambiado==false){
                       p.setCantidad(1);
                       arrayProductos2.add(p);
                   }
               }
               cambiado=false;

               Collections.sort(arrayProductos2);
           }
       });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(created==false){
            overridePendingTransition(R.anim.slide_out_2, R.anim.slide_in_2);
        }
    }
    public void recuperarProductosServer(){
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
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                byte[] bytes = new byte[16 * 1024];
                bytes=categoria.getBytes();
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                out.close();
                socket.close();

                socket=new Socket();
                socket.connect(sockAdr, timeout);
                in = new ObjectInputStream(socket.getInputStream());
                try {
                    Object ob = in.readObject();
                    arrayProductos = (ArrayList<Producto>)ob;
                    System.out.println("--- "+arrayProductos.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                in.close();
                socket.close();
            }
        }catch (SocketTimeoutException e){
            Toast.makeText(this,"No se pudo conectar con el servidor",Toast.LENGTH_LONG).show();
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
    public void onClick(View view) {
        sonido2.start();
        startActivity(i);

    }

}

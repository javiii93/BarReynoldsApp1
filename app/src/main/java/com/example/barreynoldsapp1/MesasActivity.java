package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;
import static com.example.barreynoldsapp1.Camareros_Activity.arrayTotalProductos;
import static com.example.barreynoldsapp1.Camareros_Activity.categorias;
import static com.example.barreynoldsapp1.Camareros_Activity.host;
import static com.example.barreynoldsapp1.Camareros_Activity.nombreEmpleado;
import static com.example.barreynoldsapp1.Camareros_Activity.numMesas;
import static com.example.barreynoldsapp1.Camareros_Activity.port;
import static com.example.barreynoldsapp1.Camareros_Activity.sockAdr;
import static com.example.barreynoldsapp1.Camareros_Activity.socket;
import static com.example.barreynoldsapp1.Camareros_Activity.sonido2;
import static com.example.barreynoldsapp1.Camareros_Activity.timeout;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

public class MesasActivity extends AppCompatActivity {
    Document doc;
    ListView listViewMesas;
    ArrayList<ImageButton>arrayMesas=new ArrayList<>();
    static ArrayList<Integer>arrayMesasInacabadas=new ArrayList<>();
    Intent intent;
    ImageButton b1;
    MediaPlayer sonido;
    ObjectInputStream in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        sonido2.start();
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
        conexionServidor();
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
        //intent = new Intent(getApplicationContext(),Camareros_Activity.class);
        arrayCategorias=new ArrayList<Categoria>();
        //startActivity(intent);
    }
    public void conexionServidor(){
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // CONEXION SOCKET IP CON TIMEOUT POR SI NO PUEDE CONECTAR CON EL HOST
        try{
            sockAdr = new InetSocketAddress(host, 4447);
            socket = new Socket();
            socket.connect(sockAdr, timeout);
            if(socket.isConnected()) {
                in = new ObjectInputStream(socket.getInputStream());
                    Object ob = in.readObject();
                  arrayMesasInacabadas= (ArrayList<Integer>)ob;
                    System.out.println(arrayMesasInacabadas.toString());
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

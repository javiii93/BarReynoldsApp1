package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.barreynoldsapp1.MainActivity.host;
import static com.example.barreynoldsapp1.MainActivity.port;

public class Camareros_Activity extends AppCompatActivity implements Conexion {
    private ArrayList<Empleados> arrayEmpleados = new ArrayList<>();
    private ListView lista;
    private Intent i;
    private String rutaComandaXml="camareros.xml";
    Socket socket=new Socket();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camareros);

        conexionServidor();
        Empleados e=new Empleados("manolo","bigBoss","pdaManolo");
        Empleados e3=new Empleados("rafa","bigBoss","pdaRafa");
        Empleados e4=new Empleados("gus","bigBoss","pdagus");
         i=new Intent(this,MesasActivity.class);
        CustomAdapterEmpleados adaptador = new CustomAdapterEmpleados(arrayEmpleados, this);
        lista = findViewById(R.id.listview5);
        lista.setAdapter(adaptador);
        arrayEmpleados.add(e);
        arrayEmpleados.add(e3);
        arrayEmpleados.add(e4);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(i);
            }
        });
    }
    public void conexionServidor(){
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

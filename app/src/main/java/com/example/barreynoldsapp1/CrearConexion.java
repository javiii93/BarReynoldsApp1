package com.example.barreynoldsapp1;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static com.example.barreynoldsapp1.Camareros_Activity.host;
import static com.example.barreynoldsapp1.Camareros_Activity.port;
import static com.example.barreynoldsapp1.Camareros_Activity.timeout;

public class CrearConexion {
    static Socket socket=new Socket();
    static ObjectInputStream in;
    static ObjectOutputStream out;

    public static void conexion(){
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try{
            InetSocketAddress sockAdr = new InetSocketAddress(host, port);
            socket.connect(sockAdr, timeout);
        }
        catch (SocketTimeoutException e){
           e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CrearConexion() {

    }




}

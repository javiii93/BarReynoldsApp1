package com.example.barreynoldsapp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ObtenerObjeto {
    public static Object ob;
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket socket=new Socket();
    public void get(){
        try {
            CrearConexion.conexion();
            ob = in.readObject();

            in.close();
            socket.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

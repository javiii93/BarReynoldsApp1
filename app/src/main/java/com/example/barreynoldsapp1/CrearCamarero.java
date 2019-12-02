package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCamareros;
import static com.example.barreynoldsapp1.Camareros_Activity.host;
import static com.example.barreynoldsapp1.Camareros_Activity.port;
import static com.example.barreynoldsapp1.Camareros_Activity.timeout;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

public class CrearCamarero extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static ImageView iv;
    public EditText etUser,etPass;
    Socket socket;
    ObjectOutputStream out;
    Cambrer c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_camarero);
        iv = findViewById(R.id.imageViewFoto);
        etPass = findViewById(R.id.editText);
        etUser = findViewById(R.id.editText2);
    }
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(imageBitmap);
        }
    }
    public void CrearCamarero(View view){
        if(etPass.getText().toString().isEmpty()&&etUser.toString().isEmpty()){
            Toast.makeText(this,"ERROR: User y password vacios",Toast.LENGTH_LONG).show();
        }
        else if(etUser.getText().toString().isEmpty()){
            Toast.makeText(this,"ERROR: User vacio",Toast.LENGTH_LONG).show();
        }
        else if(etPass.getText().toString().isEmpty()){
            Toast.makeText(this,"ERROR: Password vacio",Toast.LENGTH_LONG).show();
        }
        else {
            c = new Cambrer(0,etUser.getText().toString(),etPass.getText().toString(),iv);
            arrayCamareros.add(c);
            // enviar camarero
        }

    }
    public void enviarCamarero() {
        // CONEXION SOCKET IP CON TIMEOUT POR SI NO PUEDE CONECTAR CON EL HOST
        try{
            port = 4449;
            InetSocketAddress sockAdr = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sockAdr, timeout);
            if(socket.isConnected()) {
                ObjectOutputStream salidaDatos = new ObjectOutputStream(socket.getOutputStream());
                salidaDatos.writeObject(c);
                out.close();
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
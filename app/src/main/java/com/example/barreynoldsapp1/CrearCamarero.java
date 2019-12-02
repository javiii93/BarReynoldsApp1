package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCamareros;

public class CrearCamarero extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static ImageView iv;
    public EditText etUser,etPass;
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
            Cambrer c = new Cambrer(0,etUser.getText().toString(),etPass.getText().toString(),iv);
            arrayCamareros.add(c);
            // enviar camarero
        }

    }
}

package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collections;
import static com.example.barreynoldsapp1.MyCustomAdapter.imprimirProductos;


public class ComandaActivity extends AppCompatActivity {
    public static  ArrayList<Comanda> arrayComanda = new ArrayList<>();
    public ListView lista1;
   TextView tvv;
    Button deleteBtn,addBtn;
    Document doc;
    Comanda c = new Comanda();
    int selectedRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        Collections.sort(arrayProductos2);

        MyCustomAdapter adaptador = new MyCustomAdapter(arrayProductos2, this);

        // Muestra array de productos por consola
        imprimirProductos();
        // Recoge los productos de CategoriasActivity los añade a arrayComanda
        recuperarObjeto();

        deleteBtn=(Button)findViewById(R.id.delete_btn);
        addBtn=(Button)findViewById(R.id.add_btn);
        lista1 = findViewById(R.id.listView1);
        lista1.setAdapter(adaptador);
    }

    public void recuperarObjeto() {
        ArrayList<Producto> p = (ArrayList<Producto>) getIntent().getSerializableExtra("sampleObject");
        Collections.sort(p);

        for(int i=0;i<p.size();i++){
            c.setProducto(p.get(i));
            c.setCantidad(p.get(i).getCantidad());
            c.setImagen(p.get(i).getImagen());

            Collections.sort(arrayProductos2);
        }
    }
}

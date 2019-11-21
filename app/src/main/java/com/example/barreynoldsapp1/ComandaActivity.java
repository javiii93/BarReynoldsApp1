package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.barreynoldsapp1.CategoriasActivity.created;
import static com.example.barreynoldsapp1.MyCustomAdapter.imprimirProductos;


public class ComandaActivity extends AppCompatActivity {
    public static  ArrayList<Comanda> arrayComanda = new ArrayList<>();
    public ListView lista1;
    TextView tvv;
    Button deleteBtn,addBtn;
    Document doc;
    Comanda c = new Comanda();
    int selectedRow;
    final MyCustomAdapter adaptador = new MyCustomAdapter(this);


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        Collections.sort(arrayProductos2);
        deleteBtn=(Button)findViewById(R.id.delete_btn);
        addBtn=(Button)findViewById(R.id.add_btn);
        lista1 = findViewById(R.id.listView1);


        lista1.setAdapter(adaptador);

        // Muestra array de productos por consola
        imprimirProductos();
        // Recoge los productos de CategoriasActivity los añade a arrayComanda
        Collections.sort(arrayProductos2);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeListItem(view,i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        created=false;
        super.onBackPressed();
    }

    protected void removeListItem(View rowView, final int positon) {

        final Animation animation = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
        rowView.startAnimation(animation);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                arrayProductos2.remove(positon);
                adaptador.notifyDataSetChanged();
                animation.cancel();
            }
        },1000);

    }
}
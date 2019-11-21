package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class GridCategorias extends AppCompatActivity {
    Button boton;
    RecyclerView categoriasRecyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_categorias);

        boton = findViewById(R.id.buttonMesa);

        /*adapter= MyAdapter;
        rv=(RecyclerView)findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataset);
        rv.setAdapter(mAdapter);*/
    }
}

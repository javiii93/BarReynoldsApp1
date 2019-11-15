package com.example.barreynoldsapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Camareros_Activity extends AppCompatActivity {
    private ArrayList<Empleados> arrayEmpleados = new ArrayList<>();
    private ListView lista;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camareros);

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
}

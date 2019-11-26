package com.example.barreynoldsapp1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;


import java.util.ArrayList;

public class MyCustomAdapterMain  extends BaseAdapter implements ListAdapter {
    private Context context;
    public ArrayList<Categoria>list;
    private ImageView img;
    int i=1;


    public MyCustomAdapterMain(ArrayList<Categoria> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter_main, null);
        }

        ImageButton b1 = view.findViewById(R.id.botonCategoria);
        //b1.setText(arrayCategorias.get(pos).getNombre());
        if(list.get(pos).getNombre().toLowerCase().contains("begud")){
            b1.setImageResource(R.drawable.bebidalol_1);
            b1.setContentDescription("begudes");
        }
        else if(list.get(pos).getNombre().toLowerCase().contains("plat")){
            b1.setImageResource(R.drawable.comidapng_1);
            b1.setContentDescription("plats");

        }
        else if(list.get(pos).getNombre().toLowerCase().contains("tap")){
            b1.setImageResource(R.drawable.tapaspng_1);
            b1.setContentDescription("tapes");

        }
        else if(list.get(pos).getNombre().toLowerCase().contains("entrep")){
            b1.setImageResource(R.drawable.bocadillo2_1);
            b1.setContentDescription("entrepans");

        }
        //b1.setImageResource(arrayCategorias.get(pos).getFoto());
        b1.setFocusable(false);
        // AÑADIMOS AL VIEW (BOTON) EL NOMBRE CATEGORIA
        view.setContentDescription(arrayCategorias.get(pos).getNombre());
        return view;
    }
}

package com.example.barreynoldsapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import static com.example.barreynoldsapp1.Camareros_Activity.arrayCategorias;


import java.util.ArrayList;

public class MyCustomAdapterMain  extends BaseAdapter implements ListAdapter {
    private Context context;
    public ArrayList<Categoria>list;

    public MyCustomAdapterMain(ArrayList<Categoria> list, Context context) {
        this.list = arrayCategorias;
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
        b1.setImageResource(R.drawable.bebidalol_1);
        //b1.set
        b1.setFocusable(false);

        //b1.setClickable(false);
        // AÑADIMOS AL VIEW (BOTON) EL NUMERO DE MESA
        view.setContentDescription(arrayCategorias.get(pos).getNombre());
        return view;
    }
}

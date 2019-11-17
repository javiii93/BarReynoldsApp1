package com.example.barreynoldsapp1;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapterMesas extends BaseAdapter implements ListAdapter {
    private ArrayList<Button> list = new ArrayList<>();
    private Context context;
    int i=1;

    public MyCustomAdapterMesas(ArrayList<Button> list, Context context) {
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
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter_mesas, null);
        }

        Button b1 = view.findViewById(R.id.buttonMesa);
        b1.setText("Mesa "+i);
        b1.setFocusable(false);
        b1.setClickable(false);
        // AÑADIMOS AL VIEW (BOTON) EL NUMERO DE MESA
        view.setContentDescription(String.valueOf(i));
        i++;
        return view;

    }
    public void getNumeroMesa(){

    }

}
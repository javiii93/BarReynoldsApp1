package com.example.barreynoldsapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterEmpleados extends BaseAdapter implements ListAdapter {
    private ArrayList<Empleados> list = new ArrayList<>();
    private Context context;

    public CustomAdapterEmpleados(ArrayList<Empleados> list, Context context) {
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
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_customadapterempleados, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string1);
        listItemText.setText(list.get(position).toString());
        ImageView imageView= view.findViewById(R.id.imageView4);
        imageView.setImageResource(list.get(position).getImagen());
        //Handle buttons and add onClickListeners



        return view;
    }
}

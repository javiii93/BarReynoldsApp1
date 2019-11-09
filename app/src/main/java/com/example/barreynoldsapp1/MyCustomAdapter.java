package com.example.barreynoldsapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Producto> list = new ArrayList<>();
    private Context context;
    private ImageView img;
    public MyCustomAdapter(ArrayList<Producto> list, Context context) {
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
            view = inflater.inflate(R.layout.layout_adapter, null);
        }

        img = view.findViewById(R.id.imageView2);
        img.setImageResource(list.get(position).getImagen());
        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        //por el casteo a charsequence puede fallar
        listItemText.setText(list.get(position).toString());
        //list.get(position).setImagen(img);
        //Handle buttons and add onClickListeners


        return view;

    }
    public String acortarRuta(String s){
        String result;
        for(int i=s.length()-1;i>0;i--) {

            if (s.charAt(i) == '/') {
                result=s.substring(i+1);
                System.out.println(result);
                return result;
            }
        }
        return null;
    }
}
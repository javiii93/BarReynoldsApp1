package com.example.barreynoldsapp1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

//import static com.example.barreynoldsapp1.ComandaActivity.arrayComanda;
import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    ArrayList<Producto> list = arrayProductos2;
    View view;
    private Context context;
    public MyCustomAdapter(ArrayList<Producto> list, Context context) {
        this.list = arrayProductos2;
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
    public View getView(int position, final View convertView, ViewGroup parent) {

        final int pos = position;

        view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter, null);
        }
        // Smooth Animation
        final TranslateAnimation animation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(5555);

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        //por el casteo a charsequence puede fallar
        listItemText.setText(list.get(position).toString() );

        TextView textView= (TextView) view.findViewById(R.id.textView2);
        textView.setText(String.valueOf(list.get(position).getCantidad()));

        ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
        imageView.setImageResource(list.get(position).getImagen());
        //imageView.setImageResource(list.get(position).getImagen();
        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(pos).getCantidad()==1) {
                    // Animacion Smooth Start
                    listItemText.startAnimation(animation);

                    list.remove(list.get(pos));
                }
                else {
                    list.get(pos).setCantidad(list.get(pos).getCantidad()-1);
                }

                // Este hace un refresh de el adapter
                MyCustomAdapter.this.notifyDataSetChanged();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(pos).setCantidad((list.get(pos).getCantidad()) + 1);
                MyCustomAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }


}

package com.example.barreynoldsapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Comanda> list = new ArrayList<>();
    private Context context;

    public MyCustomAdapter(ArrayList<Comanda> list, Context context) {
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

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        //por el casteo a charsequence puede fallar
        listItemText.setText(list.get(position).getProducto().toString() + " Qty: " + list.get(position).getCantidad());
        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        Button addBtn = (Button) view.findViewById(R.id.add_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(pos).getCantidad() == 1) {
                    list.remove(pos);
                    //este hace un refresh de el adapter
                    MyCustomAdapter.this.notifyDataSetChanged();
                } else {
                    list.get(pos).setCantidad((list.get(pos).getCantidad()) - 1);
                }
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

package com.example.barreynoldsapp1;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Categoria>[] arrayCategorias;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button button;
        public ViewHolder(View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.imageButton);
        }
    }

    static ArrayList<Categoria> categoriasAdapterArray;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Categoria> arrayCategorias) {
        this.categoriasAdapterArray = arrayCategorias;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

       View boton =inflater.inflate(R.layout.recycler_view_items,parent,false);

        ViewHolder viewHolder = new ViewHolder(boton);
        //viewHolder.c = context;
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categoria categoria = categoriasAdapterArray.get(position);
        final ViewHolder folderForOnClick = holder;

        Button button = holder.button;

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayCategorias.length;
    }

}
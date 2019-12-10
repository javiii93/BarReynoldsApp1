package com.example.barreynoldsapp1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import static com.example.barreynoldsapp1.CategoriasActivity.arrayProductos2;
import static com.example.barreynoldsapp1.ComandaActivity.precioTotal;
import static com.example.barreynoldsapp1.ComandaActivity.textViewTotal;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    View view;
    private Context context;
    public MyCustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayProductos2.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayProductos2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {

        final int pos = position;

        view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter, null);
        }
        // Smooth Animation

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        //por el casteo a charsequence puede fallar
        listItemText.setText(arrayProductos2.get(position).toString() );

        TextView textView= (TextView) view.findViewById(R.id.textView2);
        textView.setText(String.valueOf(arrayProductos2.get(position).getCantidad()));

        ImageView imageView= (ImageView) view.findViewById(R.id.imageView3);
        //imageView.setImageResource(arrayProductos2.get(position).getImagen());
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayProductos2.get(position).getFoto());
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        imageView.setImageBitmap(bitmap);
        //imageView.setImageResource(list.get(position).getImagen();
        //Handle buttons and add onClickListeners
        // Boton borrar
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayProductos2.get(pos).getCantidad()==1) {
                    removeListItem(parent.getChildAt(pos),pos);
                }
                else {
                    borrarProductos(pos);
                }
            }
        });
        // Boton añadir
        Button addBtn = (Button) view.findViewById(R.id.botonCategoria);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirProductos(pos);
            }
        });
        precioTotal=0;
        for(int i=0;i<arrayProductos2.size();i++){
            precioTotal+=arrayProductos2.get(i).getPrecio()*arrayProductos2.get(i).getCantidad();
            textViewTotal.setText("Total: "+String.valueOf(precioTotal));
            notifyDataSetChanged();
        }

        return view;
    }
    public static void imprimirProductos(){
        for (int i=0;i<arrayProductos2.size();i++){
            Log.d("--- List",arrayProductos2.get(i).getNombre()+"\n"+arrayProductos2.get(i).getCantidad());
        }
    }
    public void removeListItem(View rowView, final int position) {

        final Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_out_right);
        rowView.startAnimation(animation);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                    arrayProductos2.remove(position);
                    MyCustomAdapter.this.notifyDataSetChanged();
                    animation.cancel();
            }
        },300);

    }
    public void borrarProductos(int pos){
        arrayProductos2.get(pos).setCantidad(arrayProductos2.get(pos).getCantidad()-1);
        MyCustomAdapter.this.notifyDataSetChanged();

    }
    public void añadirProductos(int pos){
        arrayProductos2.get(pos).setCantidad((arrayProductos2.get(pos).getCantidad()) + 1);
        MyCustomAdapter.this.notifyDataSetChanged();
    }

}

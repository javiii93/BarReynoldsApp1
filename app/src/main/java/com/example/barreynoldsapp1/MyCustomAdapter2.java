package com.example.barreynoldsapp1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.annotation.RequiresApi;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayTotalProductos;
import static com.example.barreynoldsapp1.MainActivity.categoria;

public class MyCustomAdapter2 extends BaseAdapter implements ListAdapter {
    private ArrayList<Producto> list ;
    private Context context;
   private CategoriasActivity ca;
    public MyCustomAdapter2(ArrayList<Producto> list, Context context) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter_2, null);
        }
        Log.d("-------",categoria);

        if (list.get(pos).getCategoriaNombre().toLowerCase().contains(categoria)) {
                ImageView img = view.findViewById(R.id.imageView2);
                System.out.println(list.get(pos).getFoto());
                //img.setImageResource(list.get(pos).getImagen());
                // Imagenes de la BBD al producto
                byte[]array=list.get(position).getFoto();
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(array);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//            bitmap.compress(Bitmap.CompressFormat.JPEG,0,out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                img.setImageBitmap(bitmap);
                //Handle TextView and display string from your list
                TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
                //por el casteo a charsequence puede fallar
                listItemText.setText(list.get(position).toString());
            }

            return view;




    }


}
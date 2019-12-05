package com.example.barreynoldsapp1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import androidx.annotation.RequiresApi;

import static com.example.barreynoldsapp1.Camareros_Activity.arrayCamareros;

public class CustomAdapterEmpleados extends BaseAdapter implements ListAdapter {
    private ArrayList<Cambrer> list = new ArrayList<>();
    private Context context;
    int i=0;
    public CustomAdapterEmpleados(ArrayList<Cambrer> list, Context context) {
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
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_customadapterempleados, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView4) ;
        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string1);
        listItemText.setText(list.get(position).toString());
       /* if(list.get(position).toString().toLowerCase().contains("sergio")){
            imageView.setImageResource(R.drawable.sbereno);
        }
        else if(list.get(position).toString().toLowerCase().contains("javi")){
            imageView.setImageResource(R.drawable.jlinde);
        }
        else if(list.get(position).toString().toLowerCase().contains("adri")){
            imageView.setImageResource(R.drawable.agonzalez);
        }*/
            // Foto null
            try {
                System.out.println(arrayCamareros.get(position).getFoto());
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(list.get(position).foto);
                Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
                imageView.setImageBitmap(bitmap);
            }
            catch(Exception e){

            }



        //Handle buttons and add onClickListeners


        i++;
        return view;
    }
}

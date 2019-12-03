package com.example.barreynoldsapp1;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import static com.example.barreynoldsapp1.MesasActivity.arrayMesasInacabadas;

public class MyCustomAdapterMesas extends BaseAdapter implements ListAdapter {
    private ArrayList<ImageButton> list = new ArrayList<>();
    private Context context;
    int i=1;


    public MyCustomAdapterMesas(ArrayList<ImageButton> list, Context context) {
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter_mesas, null);
        }

        ImageButton b1 = view.findViewById(R.id.buttonMesa);
        TextView t1 = view.findViewById(R.id.textView);

        t1.setText("Mesa "+i);
        b1.setFocusable(false);
        b1.setClickable(false);

        // AÑADIMOS AL VIEW (BOTON) EL NUMERO DE MESA
        view.setContentDescription(String.valueOf(i));
        for(int j=0;j<arrayMesasInacabadas.size();j++){
            if(arrayMesasInacabadas.get(j)==i){
                Log.d("Color Mesa","Verde"+i);
                b1.setBackground(ContextCompat.getDrawable(context, R.drawable.barraverde));
            }else{
                //b1.setBackground(ContextCompat.getDrawable(context, R.drawable.barragris));
                Log.d("Color Mesa","Gris"+i);

            }
        }

        i++;
        return view;

    }
    public void getNumeroMesa(){

    }

}
package com.example.barreynoldsapp1;

import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;

public class Producto implements Comparable<Producto>, Serializable {
    private String nombre;
    private float precio;
    private String descripcion, categoria;
    private int cantidad;
    private int imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;

    }

    public Producto() {
    }

    public Producto(String nombre, float precio, String descripcion, String categoria, int cantidad, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public String toString() {
        return nombre +
                "\n" + precio + "€"
                //"Cantidad: " + cantidad +
                ;
    }

   public int compareTo(Producto o) {
       String stringThis = getNombre();
       String stringO = getNombre();
       if (!stringThis.equalsIgnoreCase(stringO)) {
           if (stringThis.charAt(0) < stringO.charAt(0)) {
               return -1;
           } else if (stringThis.charAt(0) > stringO.charAt(0)) {
               return 1;
           } else {
               if (stringThis.length() > stringO.length()) {
                   for (int i = 1; i < getNombre().length(); i++) {
                       if (stringThis.charAt(i) < stringO.charAt(i)) {
                           return -1;
                       } else if (stringThis.charAt(i) > stringO.charAt(i)) {
                           return 1;
                       }
                   }
               } else {
                   for (int i = 1; i < stringO.length(); i++) {
                       if (stringThis.charAt(i) < stringO.charAt(i)) {
                           return -1;
                       } else if (stringThis.charAt(i) > stringO.charAt(i)) {
                           return 1;
                       }
                   }
               }
           }
       }
       return 0;
   }
}

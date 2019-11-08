package com.example.barreynoldsapp1;

import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

public class Producto {
    private String nombre;
    private float precio;
    private String descripcion, categoria;
    private int cantidad;
    private String imagen;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
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

    public Producto(String nombre, float precio, String descripcion, String categoria, int cantidad, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre +
                "\nPrecio: "+precio +"€"
                //"Cantidad: " + cantidad +
                +"\n"+ R.drawable.aquarius
                ;
    }
}
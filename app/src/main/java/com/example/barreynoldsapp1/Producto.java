package com.example.barreynoldsapp1;

import android.media.Image;

import java.io.Serializable;

public class Producto implements Comparable<Producto>, Serializable {
    private String nombre;
    private float precio;
    private String descripcion, categoria;
    private int cantidad;
    Image imagen;

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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
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

    @Override
    public String toString() {
        return nombre + ' ' + precio + '€';
                // ", descripcion='" + descripcion + '\'' +
                // ", categoria='" + categoria + '\'' +
                //  ", cantidad=" + cantidad +
                // ", imagen=" + imagen +
             //   ;
    }

    @Override
    public int compareTo(Producto o) {
        if (this.categoria.charAt(0) < o.categoria.charAt(0)) {
            return -1;
        } else if (this.categoria.charAt(0) < o.categoria.charAt(0)) {
            return 1;
        }
        return 0;


    }
}

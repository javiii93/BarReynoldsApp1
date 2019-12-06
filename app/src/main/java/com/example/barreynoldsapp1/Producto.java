package com.example.barreynoldsapp1;

import java.io.Serializable;

public class Producto implements Comparable<Producto>, Serializable {
    private int id;
    private String nombre;
    private float precio;
    private String descripcion, categoria;
    private String categoriaNombre;
    private int cantidad;
    private byte[] foto;

    private static final long serialVersionUID = 1733521708430895847L;

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    public Producto(String nombre, float precio, String descripcion, String categoria, int cantidad, byte[] foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.foto = foto;
    }

    public Producto(int id, String nombre, float precio, String descripcion, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Producto(int id, String nombre, float precio, String descripcion, String categoria, String categoriaNombre, int cantidad, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.categoriaNombre = categoriaNombre;
        this.cantidad = cantidad;
        this.foto = foto;
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

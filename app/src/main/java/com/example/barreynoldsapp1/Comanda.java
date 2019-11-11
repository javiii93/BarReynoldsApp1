package com.example.barreynoldsapp1;

import android.widget.ImageView;

public class Comanda implements Comparable<Comanda> {
    private Producto producto;
    private int cantidad;
    private int imagen;

    public Comanda(Producto producto, int cantidad,int imagen) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.imagen= imagen;

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return producto.toString() + cantidad;
    }

    @Override
    public int compareTo(Comanda o) {
        String stringThis = this.getProducto().getNombre();
        String stringO = o.producto.getNombre();
        if (!stringThis.equalsIgnoreCase(stringO)) {
            if (stringThis.charAt(0) < stringO.charAt(0)) {
                return -1;
            } else if (stringThis.charAt(0) > stringO.charAt(0)) {
                return 1;
            } else {
                if (stringThis.length() > stringO.length()) {
                    for (int i = 1; i < this.getProducto().getNombre().length(); i++) {
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

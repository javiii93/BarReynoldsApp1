package com.example.barreynoldsapp1;

public class Categoria {
    String nombre;
    int id;
    int foto;

    public Categoria(String nombre, int id,int foto) {
        this.nombre = nombre;
        this.id = id;
        this.foto=foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}

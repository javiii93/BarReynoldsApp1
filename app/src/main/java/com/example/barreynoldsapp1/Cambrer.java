package com.example.barreynoldsapp1;

import android.widget.ImageView;

import java.io.Serializable;

public class Cambrer implements Serializable {
    private int id;
    private String nom_Cambrer;
    public String password;
    public ImageView foto;

    private static final long serialVersionUID = 1733521708430895847L;

    public Cambrer(int id, String nom_Cambrer, String password, ImageView foto) {
        this.id = id;
        this.nom_Cambrer = nom_Cambrer;
        this.password = password;
        this.foto = foto;
    }

    public String getNom_Cambrer() {
        return nom_Cambrer;
    }

    public void setNom_Cambrer(String nom_Cambrer) {
        this.nom_Cambrer = nom_Cambrer;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nom_Cambrer;
    }

    public void setNombre(String nombre) {
        this.nom_Cambrer = nombre;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  id +"\n"+nom_Cambrer;
    }

}

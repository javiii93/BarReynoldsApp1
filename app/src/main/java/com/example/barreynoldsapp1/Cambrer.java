package com.example.barreynoldsapp1;

import java.io.Serializable;

public class Cambrer implements Serializable {
    private int id;
    private String nom_Cambrer;
    private static final long serialVersionUID = 1733521708430895847L;

    public Cambrer(int id,String nom_cambrer) {
        super();
        this.id=id;
        this.nom_Cambrer = nom_cambrer;
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


    @Override
    public String toString() {
        return  id +"\n"+nom_Cambrer;
    }

}

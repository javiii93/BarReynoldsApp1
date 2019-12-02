package com.example.barreynoldsapp1;

import java.io.Serializable;

public class Cambrer implements Serializable {
    private int id;
    private String nom_Cambrer;
    public String password;
    private static final long serialVersionUID = 1733521708430895847L;

    public Cambrer(int id,String nom_cambrer, String password) {
        super();
        this.id=id;
        this.nom_Cambrer = nom_cambrer;
        this.password= password;
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

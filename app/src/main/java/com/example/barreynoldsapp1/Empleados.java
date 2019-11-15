package com.example.barreynoldsapp1;

public class Empleados {
    private int id;
private String nombre;
private String cargo;
private String nombrePda;
    private int imagen;

    public Empleados(String nombre, String cargo, String nombrePda ) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.nombrePda = nombrePda;

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombrePda() {
        return nombrePda;
    }

    public void setNombrePda(String nombrePda) {
        this.nombrePda = nombrePda;
    }

    @Override
    public String toString() {
        return  nombre+" \n" + cargo+"\n" + nombrePda ;
    }
}

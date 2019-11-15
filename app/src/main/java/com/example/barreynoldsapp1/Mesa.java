package com.example.barreynoldsapp1;

public class Mesa {
    int id;
    int camareroId;

    public Mesa(int id, int camareroId) {
        this.id = id;
        this.camareroId = camareroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCamareroId() {
        return camareroId;
    }

    public void setCamareroId(int camareroId) {
        this.camareroId = camareroId;
    }
}

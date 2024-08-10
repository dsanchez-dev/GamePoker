package com.example.game;

public class Tarjeta {
    private int idRecursoImagen;
    private String nombre;
    private boolean volteada;
    private boolean emparejada;

    public Tarjeta(int idRecursoImagen, String nombre) {
        this.idRecursoImagen = idRecursoImagen;
        this.nombre = nombre;
        this.volteada = false;
        this.emparejada = false;
    }

    public int getIdRecursoImagen() {
        return idRecursoImagen;
    }

    public boolean estaVolteada() {
        return volteada;
    }

    public void setVolteada(boolean volteada) {
        this.volteada = volteada;
    }

    public boolean estaEmparejada() {
        return emparejada;
    }

    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }

    public String getNombre() {
        return nombre;
    }
}
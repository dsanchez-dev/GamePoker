package com.example.game;

/**
 * Esta clase representa una tarjeta en el juego.
 */
public class Tarjeta {
    // ID del recurso de la imagen de la tarjeta
    private int idRecursoImagen;
    // Nombre de la tarjeta
    private String nombre;
    // Indica si la tarjeta está volteada
    private boolean volteada;
    // Indica si la tarjeta ha sido emparejada
    private boolean emparejada;

    /**
     * Constructor de la clase Tarjeta.
     * @param idRecursoImagen ID del recurso de la imagen de la tarjeta.
     * @param nombre Nombre de la tarjeta.
     */
    public Tarjeta(int idRecursoImagen, String nombre) {
        this.idRecursoImagen = idRecursoImagen;
        this.nombre = nombre;
        this.volteada = false;
        this.emparejada = false;
    }

    /**
     * Devuelve el ID del recurso de la imagen de la tarjeta.
     * @return ID del recurso de la imagen de la tarjeta.
     */
    public int getIdRecursoImagen() {
        return idRecursoImagen;
    }

    /**
     * Indica si la tarjeta está volteada.
     * @return Verdadero si la tarjeta está volteada, falso en caso contrario.
     */
    public boolean estaVolteada() {
        return volteada;
    }

    /**
     * Establece si la tarjeta está volteada.
     * @param volteada Verdadero si la tarjeta está volteada, falso en caso contrario.
     */
    public void setVolteada(boolean volteada) {
        this.volteada = volteada;
    }

    /**
     * Indica si la tarjeta ha sido emparejada.
     * @return Verdadero si la tarjeta ha sido emparejada, falso en caso contrario.
     */
    public boolean estaEmparejada() {
        return emparejada;
    }

    /**
     * Establece si la tarjeta ha sido emparejada.
     * @param emparejada Verdadero si la tarjeta ha sido emparejada, falso en caso contrario.
     */
    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }

    /**
     * Devuelve el nombre de la tarjeta.
     * @return Nombre de la tarjeta.
     */
    public String getNombre() {
        return nombre;
    }
}
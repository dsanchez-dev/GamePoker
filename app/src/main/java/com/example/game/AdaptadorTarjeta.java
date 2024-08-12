package com.example.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Esta clase es un adaptador personalizado para el objeto Tarjeta.
 * Extiende de BaseAdapter.
 */
public class AdaptadorTarjeta extends BaseAdapter {
    // Contexto de la aplicación
    private Context contexto;
    // Lista de tarjetas a mostrar
    private List<Tarjeta> tarjetas;

    /**
     * Constructor de la clase AdaptadorTarjeta.
     * @param contexto Contexto de la aplicación.
     * @param tarjetas Lista de tarjetas a mostrar.
     */
    public AdaptadorTarjeta(Context contexto, List<Tarjeta> tarjetas) {
        this.contexto = contexto;
        this.tarjetas = tarjetas;
    }

    /**
     * Devuelve el número de elementos en la lista de tarjetas.
     * @return Número de elementos en la lista de tarjetas.
     */
    @Override
    public int getCount() {
        return tarjetas.size();
    }

    /**
     * Devuelve el objeto Tarjeta en la posición especificada.
     * @param posicion Posición del objeto Tarjeta a devolver.
     * @return Objeto Tarjeta en la posición especificada.
     */
    @Override
    public Object getItem(int posicion) {
        return tarjetas.get(posicion);
    }

    /**
     * Devuelve el ID del objeto Tarjeta en la posición especificada.
     * En este caso, el ID es la misma posición.
     * @param posicion Posición del objeto Tarjeta.
     * @return ID del objeto Tarjeta.
     */
    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /**
     * Devuelve la vista de la tarjeta en la posición especificada.
     * @param posicion Posición de la tarjeta.
     * @param vistaConvertida Vista a reutilizar, si es posible.
     * @param padre Vista padre a la que se adjuntará la vista de la tarjeta.
     * @return Vista de la tarjeta en la posición especificada.
     */
    @Override
    public View getView(int posicion, View vistaConvertida, ViewGroup padre) {
        Tarjeta tarjeta = tarjetas.get(posicion);
        View vista = vistaConvertida;

        if (vista == null) {
            LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflador.inflate(R.layout.card_item, null);
        }

        ImageView imagenTarjeta = vista.findViewById(R.id.imagenTarjeta);
        imagenTarjeta.setImageResource(tarjeta.estaVolteada() ? tarjeta.getIdRecursoImagen() : R.drawable.poker_back);

        return vista;
    }
}
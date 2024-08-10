package com.example.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class AdaptadorTarjeta extends BaseAdapter {
    private Context contexto;
    private List<Tarjeta> tarjetas;

    public AdaptadorTarjeta(Context contexto, List<Tarjeta> tarjetas) {
        this.contexto = contexto;
        this.tarjetas = tarjetas;
    }

    @Override
    public int getCount() {
        return tarjetas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return tarjetas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

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
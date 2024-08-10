package com.example.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActividadJuego extends AppCompatActivity {
    private String nombreJugador;
    private int movimientos = 0;
    private TextView bienvenidaTextView;
    private GridView cuadriculaVista;
    private Button botonReiniciar;
    private List<Tarjeta> tarjetas;
    private Tarjeta ultimaTarjetaSeleccionada = null;
    private boolean esTarjetaVolteada = false;
    private int conteoTurnos = 0;

    private TextView temporizadorTextView;
    private CountDownTimer temporizadorJuego;

    private TextView movimientosTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nombreJugador = getIntent().getStringExtra("PLAYER_NAME");
        bienvenidaTextView = findViewById(R.id.tituloJuegoTextView);
        cuadriculaVista = findViewById(R.id.cuadriculaVista);
        botonReiniciar = findViewById(R.id.botonReiniciar);
        movimientosTextView = findViewById(R.id.movimientosTextView);
        temporizadorTextView = findViewById(R.id.temporizadorTextView);
        configurarTableroJuego();

        bienvenidaTextView.setText("Bienvenido, " + nombreJugador + "!");
        configurarTableroJuego();

        botonReiniciar.setOnClickListener(v -> reiniciarJuego());

        Button botonSalir = findViewById(R.id.botonSalir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarTableroJuego() {
    try {
        tarjetas = obtenerTarjetasMezcladas();
        AdaptadorTarjeta adaptador = new AdaptadorTarjeta(this, tarjetas);
        cuadriculaVista.setAdapter(adaptador);

        cuadriculaVista.setOnItemClickListener((parent, view, position, id) -> {
            Tarjeta tarjetaSeleccionada = tarjetas.get(position);
            manejarSeleccionTarjeta(tarjetaSeleccionada);
        });

        temporizadorJuego = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                temporizadorTextView.setText("Tiempo: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                temporizadorTextView.setText("Tiempo: 0s");
                Intent intent = new Intent(ActividadJuego.this, ActividadPerder.class);
                startActivity(intent);
                finish();
            }
        }.start();
    } catch (Exception e) {
        // Aquí puedes manejar la excepción, por ejemplo, mostrando un mensaje al usuario
        Toast.makeText(this, "Ha ocurrido un error al configurar el tablero de juego. Regresando a la pantalla de inicio.", Toast.LENGTH_SHORT).show();
        e.printStackTrace();

        // Iniciar la ActividadPrincipal nuevamente
        Intent intent = new Intent(this, ActividadPrincipal.class);
        startActivity(intent);
        finish();
    }
}

    private List<Tarjeta> obtenerTarjetasMezcladas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(new Tarjeta(R.drawable.poker_5, "Cinco de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_5, "Cinco de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_6, "Seis de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_6, "Seis de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_7, "Siete de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_7, "Siete de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_8, "Ocho de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_8, "Ocho de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_9, "Nueve de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_9, "Nueve de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_joker, "Joker"));
        tarjetas.add(new Tarjeta(R.drawable.poker_joker, "Joker"));
        tarjetas.add(new Tarjeta(R.drawable.poker_king, "Rey de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_king, "Rey de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_queen, "Reina de Corazones"));
        tarjetas.add(new Tarjeta(R.drawable.poker_queen, "Reina de Corazones"));

        Collections.shuffle(tarjetas);
        return tarjetas;
    }

    private void manejarSeleccionTarjeta(Tarjeta tarjetaSeleccionada) {
    if (conteoTurnos == 2) {
        return;
    }

    if (tarjetaSeleccionada.estaEmparejada()) {
        return;
    }

    if (ultimaTarjetaSeleccionada == null) {
        ultimaTarjetaSeleccionada = tarjetaSeleccionada;
        tarjetaSeleccionada.setVolteada(true);
        conteoTurnos++;
        ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
        return;
    }

    if (ultimaTarjetaSeleccionada != null) {
        tarjetaSeleccionada.setVolteada(true);
        conteoTurnos++;
        ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
        if (tarjetaSeleccionada.getNombre().equals(ultimaTarjetaSeleccionada.getNombre())) {
            tarjetaSeleccionada.setEmparejada(true);
            ultimaTarjetaSeleccionada.setEmparejada(true);
            ultimaTarjetaSeleccionada = null;
            conteoTurnos = 0;
            verificarFinJuego();
        } else {
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    tarjetaSeleccionada.setVolteada(false);
                    ultimaTarjetaSeleccionada.setVolteada(false);
                    ultimaTarjetaSeleccionada = null;
                    conteoTurnos = 0;
                    ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
                }
            }.start();
        }
    }

    movimientos++;
    movimientosTextView.setText("Movimientos: " + movimientos);
}

    private void reiniciarJuego() {
        for (Tarjeta tarjeta : tarjetas) {
            tarjeta.setVolteada(false);
            tarjeta.setEmparejada(false);
        }
        Collections.shuffle(tarjetas);
        ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
        movimientos = 0;
        movimientosTextView.setText("Movimientos: " + movimientos);
    }

    private void finalizarJuego() {
        Intent intent = new Intent(this, ActividadResultado.class);
        intent.putExtra("PLAYER_NAME", nombreJugador);
        intent.putExtra("MOVES", movimientos);
        startActivity(intent);
    }

    private void verificarFinJuego() {
        // Verificar si todas las tarjetas han sido emparejadas
        boolean todasEmparejadas = true;
        for (Tarjeta tarjeta : tarjetas) {
            if (!tarjeta.estaEmparejada()) {
                todasEmparejadas = false;
                break;
            }
        }
        // Si todas las tarjetas han sido emparejadas, terminar el juego
        if (todasEmparejadas) {
            Intent intent = new Intent(this, ActividadGanar.class);
            startActivity(intent);
            finish();
        }
    }
}
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
    private String nombreJugador; // Nombre del jugador
    private int movimientos = 0; // Contador de movimientos realizados por el jugador
    private TextView bienvenidaTextView; // Texto que muestra la bienvenida con el nombre del jugador
    private GridView cuadriculaVista; // Vista de la cuadrícula donde se mostrarán las tarjetas
    private Button botonReiniciar; // Botón para reiniciar el juego
    private List<Tarjeta> tarjetas; // Lista que contiene las tarjetas del juego
    private Tarjeta ultimaTarjetaSeleccionada = null; // Última tarjeta que fue seleccionada por el jugador
    private boolean esTarjetaVolteada = false; // Indica si la tarjeta está volteada
    private int conteoTurnos = 0; // Contador de turnos dentro de una ronda de emparejamiento

    private TextView temporizadorTextView; // Texto que muestra el temporizador del juego
    private CountDownTimer temporizadorJuego; // Temporizador que cuenta el tiempo del juego

    private TextView movimientosTextView; // Texto que muestra el número de movimientos realizados

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Obtiene el nombre del jugador de la actividad anterior
        nombreJugador = getIntent().getStringExtra("PLAYER_NAME");

        // Inicializa las vistas y controles de la interfaz
        bienvenidaTextView = findViewById(R.id.tituloJuegoTextView);
        cuadriculaVista = findViewById(R.id.cuadriculaVista);
        botonReiniciar = findViewById(R.id.botonReiniciar);
        movimientosTextView = findViewById(R.id.movimientosTextView);
        temporizadorTextView = findViewById(R.id.temporizadorTextView);

        // Configura el tablero del juego
        configurarTableroJuego();

        // Muestra el mensaje de bienvenida con el nombre del jugador
        bienvenidaTextView.setText("Bienvenido, " + nombreJugador + "!");
        configurarTableroJuego();

        // Configura el botón de reiniciar para que reinicie el juego cuando se presione
        botonReiniciar.setOnClickListener(v -> reiniciarJuego());

        // Configura el botón de salir para terminar la actividad cuando se presione
        Button botonSalir = findViewById(R.id.botonSalir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Termina la actividad actual
            }
        });
    }

    // Configura el tablero del juego, mezclando las tarjetas y configurando el temporizador
    private void configurarTableroJuego() {
        try {
            tarjetas = obtenerTarjetasMezcladas(); // Obtiene la lista de tarjetas mezcladas
            AdaptadorTarjeta adaptador = new AdaptadorTarjeta(this, tarjetas); // Crea un adaptador para la cuadrícula
            cuadriculaVista.setAdapter(adaptador); // Asigna el adaptador a la cuadrícula

            // Configura el evento de clic para cada tarjeta en la cuadrícula
            cuadriculaVista.setOnItemClickListener((parent, view, position, id) -> {
                Tarjeta tarjetaSeleccionada = tarjetas.get(position);
                manejarSeleccionTarjeta(tarjetaSeleccionada); // Maneja la selección de la tarjeta
            });

            // Configura el temporizador del juego (2 minutos)
            temporizadorJuego = new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    temporizadorTextView.setText("Tiempo: " + millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                    // Cuando el tiempo se acaba, se dirige a la actividad de "perder"
                    temporizadorTextView.setText("Tiempo: 0s");
                    Intent intent = new Intent(ActividadJuego.this, ActividadPerder.class);
                    startActivity(intent);
                    finish();
                }
            }.start();
        } catch (Exception e) {
            // Manejo de excepción: muestra un mensaje y regresa a la pantalla principal
            Toast.makeText(this, "Ha ocurrido un error al configurar el tablero de juego. Regresando a la pantalla de inicio.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

            // Inicia la actividad principal nuevamente
            Intent intent = new Intent(this, ActividadPrincipal.class);
            startActivity(intent);
            finish();
        }
    }

    // Obtiene y mezcla las tarjetas para el juego
    private List<Tarjeta> obtenerTarjetasMezcladas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        // Añade las tarjetas al juego (pares)
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

        // Mezcla las tarjetas aleatoriamente
        Collections.shuffle(tarjetas);
        return tarjetas;
    }

    // Maneja la selección de una tarjeta por el jugador
    private void manejarSeleccionTarjeta(Tarjeta tarjetaSeleccionada) {
        // Si ya se han seleccionado dos tarjetas en esta ronda, no hacer nada
        if (conteoTurnos == 2) {
            return;
        }

        // Si la tarjeta ya está emparejada, no hacer nada
        if (tarjetaSeleccionada.estaEmparejada()) {
            return;
        }

        // Si no hay tarjeta seleccionada previamente
        if (ultimaTarjetaSeleccionada == null) {
            ultimaTarjetaSeleccionada = tarjetaSeleccionada;
            tarjetaSeleccionada.setVolteada(true); // Voltea la tarjeta seleccionada
            conteoTurnos++;
            ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
            return;
        }

        // Si ya hay una tarjeta seleccionada previamente
        if (ultimaTarjetaSeleccionada != null) {
            tarjetaSeleccionada.setVolteada(true);
            conteoTurnos++;
            ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();

            // Si las tarjetas coinciden, se emparejan
            if (tarjetaSeleccionada.getNombre().equals(ultimaTarjetaSeleccionada.getNombre())) {
                tarjetaSeleccionada.setEmparejada(true);
                ultimaTarjetaSeleccionada.setEmparejada(true);
                ultimaTarjetaSeleccionada = null;
                conteoTurnos = 0;
                verificarFinJuego(); // Verifica si el juego ha terminado
            } else {
                // Si las tarjetas no coinciden, se voltean nuevamente después de un breve tiempo
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

        // Incrementa el número de movimientos realizados y actualiza la vista
        movimientos++;
        movimientosTextView.setText("Movimientos: " + movimientos);
    }

    // Reinicia el juego a su estado inicial
    private void reiniciarJuego() {
        for (Tarjeta tarjeta : tarjetas) {
            tarjeta.setVolteada(false);
            tarjeta.setEmparejada(false);
        }
        Collections.shuffle(tarjetas); // Vuelve a mezclar las tarjetas
        ((BaseAdapter) cuadriculaVista.getAdapter()).notifyDataSetChanged();
        movimientos = 0;
        movimientosTextView.setText("Movimientos: " + movimientos);
    }

    // Finaliza el juego y envía los datos a la actividad de resultados
    private void finalizarJuego() {
        Intent intent = new Intent(this, ActividadResultado.class);
        intent.putExtra("PLAYER_NAME", nombreJugador);
        intent.putExtra("MOVES", movimientos);
        startActivity(intent);
    }

    // Verifica si todas las tarjetas han sido emparejadas y finaliza el juego si es así
    private void verificarFinJuego() {
        // Verifica si todas las tarjetas han sido emparejadas
        boolean todasEmparejadas = true;
        for (Tarjeta tarjeta : tarjetas) {
            if (!tarjeta.estaEmparejada()) {
                todasEmparejadas = false;
                break;
            }
        }
        // Si todas las tarjetas han sido emparejadas, se dirige a la actividad de "ganar"
        if (todasEmparejadas) {
            Intent intent = new Intent(this, ActividadGanar.class);
            startActivity(intent);
            finish();
        }
    }
}
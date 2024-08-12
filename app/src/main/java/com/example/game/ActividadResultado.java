package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Esta clase representa la actividad que se muestra cuando se termina el juego.
 * Extiende de AppCompatActivity.
 */
public class ActividadResultado extends AppCompatActivity {
    /**
     * Este método se llama cuando se crea la actividad.
     * Inicializa la vista y configura el botón de reinicio.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Obtiene el nombre del jugador y el número de movimientos del Intent
        String nombreJugador = getIntent().getStringExtra("PLAYER_NAME");
        int movimientos = getIntent().getIntExtra("MOVES", 0);

        // Inicializa el botón de reinicio
        Button botonReiniciar = findViewById(R.id.botonReiniciar);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            /**
             * Este método se llama cuando se hace clic en el botón de reinicio.
             * Inicia la actividad principal y finaliza esta actividad.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadResultado.this, ActividadPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
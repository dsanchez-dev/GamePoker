package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Esta clase representa la actividad que se muestra cuando el jugador gana el juego.
 * Extiende de AppCompatActivity.
 */
public class ActividadGanar extends AppCompatActivity {
    // Botón para reiniciar el juego
    private Button botonReiniciar;

    /**
     * Este método se llama cuando se crea la actividad.
     * Inicializa la vista y configura el botón de reinicio.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // Inicializa el botón de reinicio
        botonReiniciar = findViewById(R.id.botonReiniciar);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            /**
             * Este método se llama cuando se hace clic en el botón de reinicio.
             * Inicia la actividad principal y finaliza esta actividad.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadGanar.this, ActividadPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
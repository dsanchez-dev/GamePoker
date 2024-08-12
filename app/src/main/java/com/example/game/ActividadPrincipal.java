package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Esta clase representa la actividad principal de la aplicación.
 * Extiende de AppCompatActivity.
 */
public class ActividadPrincipal extends AppCompatActivity {
    // Campo de texto para ingresar el nombre del jugador
    private EditText editTextNombreJugador;
    // Botón para iniciar el juego
    private Button botonJugar;

    /**
     * Este método se llama cuando se crea la actividad.
     * Inicializa la vista y configura el campo de texto y el botón.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el campo de texto y el botón
        editTextNombreJugador = findViewById(R.id.editTextNombreJugador);
        botonJugar = findViewById(R.id.botonJugar);

        // Configura el comportamiento del campo de texto cuando se presiona la tecla Enter
        editTextNombreJugador.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    iniciarJuego();
                    return true;
                }
                return false;
            }
        });

        // Configura el comportamiento del botón cuando se hace clic en él
        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJuego();
            }
        });
    }

    /**
     * Este método inicia el juego.
     * Verifica que el nombre del jugador no esté vacío y luego inicia la actividad del juego.
     */
    private void iniciarJuego() {
        String nombreJugador = editTextNombreJugador.getText().toString();
        if (nombreJugador.isEmpty()) {
            Toast.makeText(ActividadPrincipal.this, "Por favor, ingresa tu nombre antes de iniciar el juego.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ActividadPrincipal.this, ActividadJuego.class);
            intent.putExtra("PLAYER_NAME", nombreJugador);
            startActivity(intent);
        }
    }
}
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

public class ActividadPrincipal extends AppCompatActivity {
    private EditText editTextNombreJugador;
    private Button botonJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombreJugador = findViewById(R.id.editTextNombreJugador);
        botonJugar = findViewById(R.id.botonJugar);

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

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJuego();
            }
        });
    }

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
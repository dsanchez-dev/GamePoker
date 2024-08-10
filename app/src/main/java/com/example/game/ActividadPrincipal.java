package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        botonJugar.setOnClickListener(v -> {
            String nombreJugador = editTextNombreJugador.getText().toString();
            Intent intent = new Intent(ActividadPrincipal.this, ActividadJuego.class);
            intent.putExtra("PLAYER_NAME", nombreJugador);
            startActivity(intent);
        });
    }
}
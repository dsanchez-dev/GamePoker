package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadResultado extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String nombreJugador = getIntent().getStringExtra("PLAYER_NAME");
        int movimientos = getIntent().getIntExtra("MOVES", 0);

        Button botonReiniciar = findViewById(R.id.botonReiniciar);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadResultado.this, ActividadPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
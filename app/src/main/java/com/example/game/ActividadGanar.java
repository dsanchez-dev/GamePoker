package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadGanar extends AppCompatActivity {
    private Button botonReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        botonReiniciar = findViewById(R.id.botonReiniciar);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadGanar.this, ActividadPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
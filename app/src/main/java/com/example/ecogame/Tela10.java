package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela10 extends AppCompatActivity {
    Button botaoRestart;
    Button buttonCertificated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela10);

        botaoRestart = findViewById(R.id.buttonRestart);
        buttonCertificated = findViewById(R.id.buttonCertificated);
        botaoRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela10.this, MainActivity.class);
                startActivity(in);
            }
        });
        buttonCertificated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela10.this, Tela10b.class);
                startActivity(in);
            }
        });
    }


}
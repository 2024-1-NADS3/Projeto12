package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen11_certificate extends AppCompatActivity {
    Button botaoRestart;
    Button buttonCertificated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen11_certificate);

        botaoRestart = findViewById(R.id.buttonRestart);
        buttonCertificated = findViewById(R.id.buttonCertificated);
        botaoRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen11_certificate.this, screen1_startgame.class);
                startActivity(in);
            }
        });
        buttonCertificated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen11_certificate.this, screen10_updatedata.class);
                startActivity(in);
            }
        });
    }


}
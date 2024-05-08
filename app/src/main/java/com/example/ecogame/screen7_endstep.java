package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen7_endstep extends AppCompatActivity {
    private Button botaoVoltar4;
    private Button botaoContinuar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen7_endstep);

        //Navegaçãp
        botaoVoltar4 = findViewById(R.id.buttonBack4);

        botaoVoltar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen7_endstep.this, screen6_game3.class);
                startActivity(in);
            }
        });

        botaoContinuar4 = findViewById(R.id.buttonGo4);
        botaoContinuar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen7_endstep.this, screen8_video1.class);
                startActivity(in);
            }
        });
    }
}
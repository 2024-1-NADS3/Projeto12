package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen4_lostgame4 extends AppCompatActivity {
    private Button botaoContinuar2;
    private Button buttonVoltar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_lostgame4);

        //Navigation system
        //Checar se vai manter esse button no layout final, se não, excluir
        botaoContinuar2 = findViewById(R.id.buttonGo2S4LostGame4);
        botaoContinuar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_lostgame4.this, screen5_endstep.class);
                startActivity(in);
            }
        });

        //Return to the challenge/try again
        buttonVoltar3 = findViewById(R.id.buttonBack3S4LostGame4);
        buttonVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_lostgame4.this, screen4_game4.class);
                startActivity(in);
            }
        });
    }
}
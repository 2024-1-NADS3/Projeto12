package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen6_lostgame1 extends AppCompatActivity {
    private Button botaoContinuar6e;
    private Button buttonVoltar6e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6_lostgame1);

        //Navigation system
        //Checar se vai manter esse button no layout final, se não, excluir
        botaoContinuar6e = findViewById(R.id.buttonGo6S6LostGame1);
        botaoContinuar6e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen6_lostgame1.this, screen6_game2.class);
                startActivity(in);
            }
        });

        //Return to the challenge/try again
        buttonVoltar6e = findViewById(R.id.buttonBack6S6LostGame1);
        buttonVoltar6e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen6_lostgame1.this, screen6_game1.class);
                startActivity(in);
            }
        });
    }
}
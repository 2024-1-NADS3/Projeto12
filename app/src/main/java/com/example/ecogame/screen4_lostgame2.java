package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen4_lostgame2 extends AppCompatActivity {

    private Button botaoContinuar2;
    private Button buttonVoltar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_lostgame2);

        //Return to the challenge/try again
        buttonVoltar3 = findViewById(R.id.buttonBack3S4LostGame2);
        buttonVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_lostgame2.this, screen4_game2.class);
                startActivity(in);
            }
        });
    }
}
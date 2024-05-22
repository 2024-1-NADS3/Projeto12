package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen4_lostgame3 extends AppCompatActivity {

    private Button botaoContinuar2;
    private Button buttonVoltar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_lostgame3);

        //Return to the challenge/try again
        buttonVoltar3 = findViewById(R.id.buttonBackS4LostGame3);
        buttonVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_lostgame3.this, screen4_game3.class);
                startActivity(in);
            }
        });
    }
}
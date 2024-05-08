package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen12_endgame extends AppCompatActivity {

    Button buttonT11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen12_endgame);
        buttonT11 = findViewById(R.id.buttonT11);

        buttonT11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen12_endgame.this, screen1_startgame.class);
                startActivity(in);
            }
        });
    }
}
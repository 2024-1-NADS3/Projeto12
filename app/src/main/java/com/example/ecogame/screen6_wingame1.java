package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen6_wingame1 extends AppCompatActivity {
    private Button botaoContinuar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6_wingame1);

        //Navigation system
        //Move on to the next challenge
        botaoContinuar6 = findViewById(R.id.buttonGo6S6Win1);
        botaoContinuar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), screen6_game2.class);
                startActivity(in);
            }
        });

    }
}
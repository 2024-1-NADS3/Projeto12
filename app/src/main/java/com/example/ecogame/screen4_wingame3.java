package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen4_wingame3 extends AppCompatActivity {
    private Button botaoContinuar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_wingame3);

        //Navigation system
        //Move on to the next challenge
        botaoContinuar3 = findViewById(R.id.buttonGoS4Win3);
        botaoContinuar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_wingame3.this, screen4_game4.class);
                startActivity(in);
            }
        });
    }
}
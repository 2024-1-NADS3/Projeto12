package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela7 extends AppCompatActivity {
    private Button botaoVoltar4;
    private Button botaoContinuar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela7);

        //Navegaçãp
        botaoVoltar4 = findViewById(R.id.buttonBack4);

        botaoVoltar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela7.this, Tela6.class);
                startActivity(in);
            }
        });

        botaoContinuar4 = findViewById(R.id.buttonGo4);
        botaoContinuar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela7.this, Tela8.class);
                startActivity(in);
            }
        });
    }
}
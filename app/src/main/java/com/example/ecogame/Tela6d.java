package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela6d extends AppCompatActivity {
    private Button botaoContinuar6;
    private Button buttonVoltar6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela6d);

        //Navegaçãp
        botaoContinuar6 = findViewById(R.id.buttonGo6);

        botaoContinuar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela6d.this, Tela7.class);
                startActivity(in);
            }
        });

        buttonVoltar6 = findViewById(R.id.buttonBack6);

        buttonVoltar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela6d.this, Tela6.class);
                startActivity(in);
            }
        });
    }
}
package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela3errorb extends AppCompatActivity {

    private Button botaoContinuar2;
    private Button buttonVoltar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela3errorb);

        //Navegaçãp
        botaoContinuar2 = findViewById(R.id.buttonBackT3b3);

        botaoContinuar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela3errorb.this, Tela3c.class);
                startActivity(in);
            }
        });

        buttonVoltar3 = findViewById(R.id.buttonGoT3b2);

        buttonVoltar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela3errorb.this, Tela3a.class);
                startActivity(in);
            }
        });
    }
}
package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela6e extends AppCompatActivity {
    private Button botaoContinuar6e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela6e);

        //Navegaçãp
        botaoContinuar6e = findViewById(R.id.buttonGo6e);
        botaoContinuar6e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela6e.this, Tela7.class);
                startActivity(in);
            }
        });
    }
}
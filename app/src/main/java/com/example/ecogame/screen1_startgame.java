package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen1_startgame extends AppCompatActivity {
    private Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1_startgame);

        //Navegaçãp
        botao = findViewById(R.id.buttonStart);

        botao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen1_startgame.this, screen2_login.class);
                startActivity(in);
            }
        });
    }
}
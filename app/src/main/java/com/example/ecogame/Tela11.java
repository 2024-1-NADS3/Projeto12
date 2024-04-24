package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela11 extends AppCompatActivity {

    Button buttonT11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela11);
        buttonT11 = findViewById(R.id.buttonT11);

        buttonT11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela11.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
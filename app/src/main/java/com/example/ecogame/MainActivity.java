package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navegaçãp
        botao = findViewById(R.id.buttonStart);

        botao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Tela1.class);
                startActivity(in);
            }
        });
    }
}
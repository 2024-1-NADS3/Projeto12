package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela3congrats extends AppCompatActivity {

    private Button botaoContinuar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela3congrats);

        //Navegaçãp
        botaoContinuar3 = findViewById(R.id.buttonGo3);
        botaoContinuar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela3congrats.this, Tela6.class);
                startActivity(in);
            }
        });
    }


}
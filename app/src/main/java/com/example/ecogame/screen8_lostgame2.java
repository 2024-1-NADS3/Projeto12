package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_lostgame2 extends Activity {
    private Button botaoContinuar;
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_lostgame2);

        //Navegaçãp
        botaoContinuar = findViewById(R.id.buttonGo2S8LostGame2);
        botaoVoltar = findViewById(R.id.buttonBack3S8LostGame2);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame2.this, screen7_endstep.class);
                startActivity(in);
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame2.this, screen7_endstep.class);
                startActivity(in);
            }
        });
    }
}

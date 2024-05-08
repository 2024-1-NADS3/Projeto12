package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_lostgame4 extends Activity {
    private Button botaoContinuar;
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_lostgame4);

        //Navegaçãp
        botaoContinuar = findViewById(R.id.buttonGo2S8LostGame4);
        botaoVoltar = findViewById(R.id.buttonBack3S8LostGame4);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame4.this, screen7_endstep.class);
                startActivity(in);
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame4.this, screen7_endstep.class);
                startActivity(in);
            }
        });
    }
}

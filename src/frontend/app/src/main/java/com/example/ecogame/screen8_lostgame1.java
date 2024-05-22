package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_lostgame1 extends Activity {
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_lostgame1);

        //Navegaçãp
        botaoVoltar = findViewById(R.id.buttonBack3S8LostGame1);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame1.this, screen8_game1.class);
                startActivity(in);
            }
        });
    }
}

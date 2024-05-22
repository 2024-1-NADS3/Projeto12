package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_lostgame3 extends Activity {
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_lostgame3);

        //Navegaçãp
        botaoVoltar = findViewById(R.id.buttonGo2S8LostGame2);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_lostgame3.this, screen8_game3.class);
                startActivity(in);
            }
        });
    }
}

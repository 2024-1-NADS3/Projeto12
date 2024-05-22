package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_wingame2 extends Activity {
    private Button botaoContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_wingame2);

        //Navegaçãp
        botaoContinuar = findViewById(R.id.buttonGo6eS8GameWin2);

        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_wingame2.this, screen8_video3.class);
                startActivity(in);
            }
        });
    }
}

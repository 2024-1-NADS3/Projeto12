package com.example.ecogame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen8_wingame3 extends Activity {
    private Button botaoContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_wingame3);

        //Navegaçãp
        botaoContinuar = findViewById(R.id.buttonGoS8GameWin3);

        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_wingame3.this, screen8_video4.class);
                startActivity(in);
            }
        });
    }
}


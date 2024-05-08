package com.example.ecogame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class screen6_wingame2 extends AppCompatActivity {
    private Button botaoContinuar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6_wingame2);

        //Navegaçãp
        botaoContinuar6 = findViewById(R.id.buttonGoS6Win2);
        botaoContinuar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen6_wingame2.this, screen6_game3.class);
                startActivity(in);
            }
        });
    }
}
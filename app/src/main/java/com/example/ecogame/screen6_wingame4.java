package com.example.ecogame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class screen6_wingame4 extends AppCompatActivity {
    private Button botaoContinuar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6_wingame4);

        //Navigation system
        botaoContinuar6 = findViewById(R.id.buttonGoS6Win4);

        botaoContinuar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen6_wingame4.this, screen7_endstep.class);
                startActivity(in);
            }
        });

    }
}
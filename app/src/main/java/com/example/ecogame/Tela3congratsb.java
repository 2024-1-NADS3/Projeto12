package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela3congratsb extends AppCompatActivity {
    private Button botaoContinuar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela3congratsb);

        //Navegaçãp
        botaoContinuar3 = findViewById(R.id.buttonGoT3congratsc3);
        botaoContinuar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Tela3c.class);
                startActivity(in);
            }
        });
    }
}
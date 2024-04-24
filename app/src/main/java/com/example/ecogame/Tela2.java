package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Tela2 extends AppCompatActivity {
    private Button botaoVoltar;
    private Button botaoContinuar;
    TextView textView2;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);

        // Navegação
        botaoVoltar = findViewById(R.id.buttonBack);
        textView2 = findViewById(R.id.textView2);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela2.this, MainActivity.class);
                startActivity(in);
            }
        });

        botaoContinuar = findViewById(R.id.buttonCadastrart1);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Tela2.this, Tela3.class);
                startActivity(in);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        consultarUltimoNickname();
    }

    // Consultar último nickname do servidor
    public void consultarUltimoNickname() {
        String url = "https://nnn5h2-3000.csb.app/ultimo-nickname";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nickname = response.getString("nickname");
                            if (nickname != null) {
                                textView2.setText(nickname);
                            } else {
                                textView2.setText("Não há nenhum nickname cadastrado.");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView2.setText("Erro ao consultar o último nickname.");
                    }
                });

        requestQueue.add(request);
    }
}

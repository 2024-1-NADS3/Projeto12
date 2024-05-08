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

public class screen5_endstep extends AppCompatActivity {

    private Button buttonBackT4;
    private Button buttonGoT4;
    TextView textViewT4three;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen5_endstep);

        buttonBackT4 = findViewById(R.id.buttonBackT4);
        buttonGoT4 = findViewById(R.id.buttonGoT4);
        textViewT4three = findViewById(R.id.textViewT4three);

        //Navigation system
        buttonBackT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), screen3_welcome.class);
                startActivity(in);
            }
        });

        buttonGoT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), screen6_game1.class);
                startActivity(in);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        consultarUltimoNickname();
    }

    // Consultar último nickname do servidor
    private void consultarUltimoNickname() {
        String url = "https://nnn5h2-3000.csb.app/ultimo-nickname";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nickname = response.getString("nickname");
                            if (nickname != null) {
                                textViewT4three.setText(nickname);
                            } else {
                                textViewT4three.setText("Não há nenhum nickname cadastrado.");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewT4three.setText("Erro ao consultar o último nickname.");
                    }
                });

        requestQueue.add(request);
    }
}
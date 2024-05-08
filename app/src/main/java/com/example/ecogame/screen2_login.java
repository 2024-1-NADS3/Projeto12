package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class screen2_login extends AppCompatActivity {
    EditText editTextNomeCompleto;
    EditText editTextNickName;
    Button buttonCadastrart1;

    RequestQueue filaRequest;

    String url = "https://nnn5h2-3000.csb.app/usuarios1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2_login);

        filaRequest = Volley.newRequestQueue(this);

        editTextNomeCompleto = findViewById(R.id.editTextNomeCompleto);
        editTextNickName = findViewById(R.id.editTextNickName);
        buttonCadastrart1 = findViewById(R.id.buttonCadastrart1);

        buttonCadastrart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNomeCompleto.getText().toString();
                String nickname = editTextNickName.getText().toString();

                if (!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(nickname)) {
                    enviarDadosParaServidor(nome, nickname);
                    Intent in = new Intent(screen2_login.this, screen3_welcome.class);
                    startActivity(in);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Cadastrar o usuário no banco de dados
    public void enviarDadosParaServidor(final String nome, final String nickname) {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nome", nome);
            jsonBody.put("nickname", nickname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Tratar a resposta do servidor, se necessário
                Log.d("Response", response.toString());
                Toast.makeText(getApplicationContext(), "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Tratar erros de comunicação com o servidor
                Log.e("Error", error.toString());
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar dados. Por favor, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            }
        };

        filaRequest.add(request);
    }


}

package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class screen8_game1 extends AppCompatActivity {

    private ImageView garbageRed;
    private ImageView garbageGreen;
    private ImageView garbageMarron;
    private ImageView garbageWhite;
    private int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_game1);

        garbageRed = findViewById(R.id.garbageRed2S8Game1);
        garbageGreen = findViewById(R.id.garbageGreen2S8Game1);
        garbageMarron = findViewById(R.id.garbageMarron2S8Game1);
        garbageWhite = findViewById(R.id.garbageWhite2S8Game1);

        consultarUltimoId();

        garbageRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen8_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });
        garbageGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen8_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });
        garbageMarron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen8_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });
        garbageWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, true);
                Intent in = new Intent(screen8_game1.this, screen11_certificate.class);
                startActivity(in);
            }
        });
    }
    // Função para consultar e salvar o último ID cadastrado na tabela "nomes"
    private void consultarUltimoId() {
        String url = "https://nnn5h2-3000.csb.app/ultimo-id";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int id = response.optInt("id", -1); // Usar optInt para lidar com valores nulos
                        if (id != -1) {
                            userId = id; // Salvar o último ID na variável userIdd
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tratar erro ao consultar o último ID
                    }
                });

        // Adicionar a requisição à fila
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    // Método para contabilizar "falso" no banco de dados
    private void saveFalse(int userId, boolean corI) {
        // URL para enviar a solicitação POST para o servidor
        String url = "https://nnn5h2-3000.csb.app/usuarios/" + userId + "/cores";

        // Objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_i", corI);

            // Solicitação POST usando Volley para enviar os dados para o servidor
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Trata a resposta bem-sucedida
                                String message = response.getString("message");
                                Log.d("Resposta Bem-Sucedida", "Mensagem: " + message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Trata a resposta com erro
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Log.e("Erro de Conexão", "Erro de tempo limite ou conexão");
                            } else if (error instanceof AuthFailureError) {
                                Log.e("Erro de Autenticação", "Erro de autenticação");
                            } else if (error instanceof ServerError) {
                                Log.e("Erro do Servidor", "Erro do servidor");
                            } else if (error instanceof NetworkError) {
                                Log.e("Erro de Rede", "Erro de rede");
                            } else if (error instanceof ParseError) {
                                Log.e("Erro de Análise", "Erro de análise");
                            }
                        }
                    });

            // Adiciona a solicitação à fila de solicitações Volley
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Método para contabilizar "verdadeiro" no banco de dados
    private void saveTrue(int userId, boolean corI) {
        // Constrói o URL para enviar a solicitação POST para o servidor
        String url = "https://nnn5h2-3000.csb.app/usuarios/" + userId + "/cores";

        // Cria um objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_i", corI);

            // Cria uma solicitação POST usando Volley para enviar os dados para o servidor
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Tratar a resposta bem-sucedida
                                String message = response.getString("message");
                                Log.d("Resposta Bem-Sucedida", "Mensagem: " + message);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Tratar a resposta com erro
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Log.e("Erro de Conexão", "Erro de tempo limite ou conexão");
                            } else if (error instanceof AuthFailureError) {
                                Log.e("Erro de Autenticação", "Erro de autenticação");
                            } else if (error instanceof ServerError) {
                                Log.e("Erro do Servidor", "Erro do servidor");
                            } else if (error instanceof NetworkError) {
                                Log.e("Erro de Rede", "Erro de rede");
                            } else if (error instanceof ParseError) {
                                Log.e("Erro de Análise", "Erro de análise");
                            }

                        }
                    });

            // Adiciona a solicitação à fila de solicitações Volley
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
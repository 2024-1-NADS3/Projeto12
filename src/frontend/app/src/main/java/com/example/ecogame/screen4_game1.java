package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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


public class screen4_game1 extends AppCompatActivity {

    private Button botaoVermelho;
    private Button botaoAmarelo;
    private Button botaoPreto;
    private Button botaoVerde;
    private Button botaoAzul;
    private Button botaoLaranja;
    private Button botaoback2;
    private ImageButton imageButtonS4Game1;
    private int userId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4_game1);

        consultarUltimoId();

        //Navigation system
        //Correct option
        botaoVermelho = findViewById(R.id.buttonRedS4Game1);
        botaoVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrue(userId,false);
                Intent in = new Intent(screen4_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });

        //Incorrect options
        botaoAmarelo = findViewById(R.id.buttonYellowS4Game1);
        botaoAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen4_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });

        botaoPreto = findViewById(R.id.buttonBlackS4Game1);
        botaoPreto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId,false);
                Intent in = new Intent(screen4_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });

        botaoVerde = findViewById(R.id.buttonGreenS4Game1);
        botaoVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, true);
                Intent in = new Intent(screen4_game1.this, screen4_wingame1.class);
                startActivity(in);
            }
        });

        botaoAzul = findViewById(R.id.buttonBlueS4Game1);
        botaoAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen4_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });
        botaoLaranja = findViewById(R.id.buttonOrangeS4Game1);
        botaoLaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFalse(userId, false);
                Intent in = new Intent(screen4_game1.this, screen4_lostgame1.class);
                startActivity(in);
            }
        });

        //Return to the beginning of the level/stage
        botaoback2 = findViewById(R.id.buttonBackS4Game1);
        botaoback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_game1.this, screen3_welcome.class);
                startActivity(in);
            }
        });
        //Return to the beginning of the level/stage
        imageButtonS4Game1 = findViewById(R.id.imageButtonS4Game1);
        imageButtonS4Game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen4_game1.this, screen1_startlogin.class);
                startActivity(in);
            }
        });
    }
    // Função para consultar e salvar o último ID cadastrado na tabela "nomes"
    private void consultarUltimoId() {
        String url = "https://6xrrfz-3000.csb.app/ultimo-id";

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
                        Log.e("Error", error.toString());
                        Toast.makeText(getApplicationContext(), "Erro ao tentar salvar o último id.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adicionar a requisição à fila
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    // Método para contabilizar "falso" no banco de dados
    private void saveFalse(int userId, boolean corA) {
        // URL para enviar a solicitação POST para o servidor
        String url = "https://6xrrfz-3000.csb.app/usuarios/" + userId + "/cores";

        // Objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_a", corA);

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
    private void saveTrue(int userId, boolean corA) {
        // Constrói o URL para enviar a solicitação POST para o servidor
        String url = "https://6xrrfz-3000.csb.app/usuarios/" + userId + "/cores";

        // Cria um objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_a", corA);

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
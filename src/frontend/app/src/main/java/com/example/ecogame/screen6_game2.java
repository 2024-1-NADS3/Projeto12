package com.example.ecogame;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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

public class screen6_game2 extends AppCompatActivity {

    private View white;
    private int userId = -1;
    private View imageView3;
    private View imageView4;
    private View imageView5;
    private View imageView6;
    private ImageButton imageButtonS6Game2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen6_game2);

        imageView3 = findViewById(R.id.imageView3S6Game2);
        imageView4 = findViewById(R.id.imageView4S6Game2);
        imageView5 = findViewById(R.id.imageView5S6Game2);
        imageView6 = findViewById(R.id.imageView6S6Game2);

        findViewById(R.id.petS6Game2).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.topleftS6Game2).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.toprightS6Game2).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.bottomleftS6Game2).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.bottomrightS6Game2).setOnDragListener(new MyOnDragListener(4));

        white = findViewById(R.id.bottomrightS6Game2);
        //Return to the beginning of the level/stage
        imageButtonS6Game2 = findViewById(R.id.imageButtonS6Game2);
        imageButtonS6Game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen6_game2.this, screen5_endstep.class);
                startActivity(in);
            }
        });
        consultarUltimoId();
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.petS6Game2));
            v.startDrag(data, sb, v, 0);
            v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;

        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("Script", num + " - ACTION_DRAG_STARTED");
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return (true);
                    }
                    return (false);
                case DragEvent.ACTION_DRAG_ENTERED:

                    Log.i("Script", num + " - ACTION_DRAG_ENTERED");
                    //v.setBackgroundColor(Color.rgb(20, 42, 73));

                    // Verifique se o item arrastado é um ImageView
                    View draggedView = (View) event.getLocalState();
                    if (draggedView instanceof ImageView) {
                        ImageView imageView = (ImageView) draggedView;

                        // Verifique se o ImageView tem uma imagem definida
                        if (imageView.getDrawable() != null) {
                            // Verifique em qual LinearLayout o ImageView está sendo arrastado
                            if (v.getId() == R.id.topleftS6Game2) {
                                //Correct option
                                saveFalse(userId, true);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(screen6_game2.this,
                                                screen6_wingame2.class);
                                        startActivity(intent);
                                    }
                                }, 1000); // Atraso de 1 segundo (1000 milissegundos)
                            } else if (v.getId() == R.id.toprightS6Game2) {
                                //Incorrect option
                                saveFalse(userId, false);
                                Intent intent = new Intent(screen6_game2.this,
                                        screen6_lostgame2.class);
                                startActivity(intent);
                            } else if (v.getId() == R.id.bottomleftS6Game2) {
                                //Incorrect option
                                saveFalse(userId, false);
                                Intent intent = new Intent(screen6_game2.this,
                                        screen6_lostgame2.class);
                                startActivity(intent);
                            } else if (v.getId() == R.id.bottomrightS6Game2) {
                                //Incorrect option
                                saveFalse(userId, false);
                                Intent intent = new Intent(screen6_game2.this,
                                        screen6_lostgame2.class);
                                startActivity(intent);
                            }
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", 2 + " - ACTION_DRAG_LOCATION");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script", num + " - ACTION_DRAG_EXITED");
                    //v.setBackgroundColor(Color.rgb(255, 255, 255));
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");
                    //v.setBackgroundColor(Color.rgb(255, 255, 255));
                    break;
            }
            return true;
        }
    }

    // Função para consultar e salvar o último ID cadastrado na tabela "nomes"
    private void consultarUltimoId() {
        String url = "https://6xrrfz-3000.csb.app/ultimo-id";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int id = response.optInt("id", -1); // Usar optInt para lidar com valores
                        // nulos
                        if (id != -1) {
                            userId = id; // Salvar o último ID na variável userIdd
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tratar erro ao consultar o último ID
                        Intent in = new Intent(getApplicationContext(), screen3_welcome.class);
                        startActivity(in);
                    }
                });

        // Adicionar a requisição à fila
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    // Método para contabilizar "falso" no banco de dados
    private void saveFalse(int userId, boolean corF) {
        // URL para enviar a solicitação POST para o servidor
        String url = "https://6xrrfz-3000.csb.app/usuarios/" + userId + "/cores";

        // Objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_f", corF);

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
    private void saveTrue(int userId, boolean corF) {
        // Constrói o URL para enviar a solicitação POST para o servidor
        String url = "https://6xrrfz-3000.csb.app/usuarios/" + userId + "/cores";

        // Cria um objeto JSON com os dados a serem enviados
        JSONObject json = new JSONObject();
        try {
            json.put("cor_f", corF);

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
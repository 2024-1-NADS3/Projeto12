package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class screen3_welcome extends AppCompatActivity {
    private Button botaoVoltar;
    private Button botaoContinuar;
    TextView textView2;
    RequestQueue requestQueue;
    VideoView videoViewWelcome;
    MediaController mycontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3_welcome);

        botaoVoltar = findViewById(R.id.buttonBack);
        textView2 = findViewById(R.id.textView2);
        videoViewWelcome = (VideoView) findViewById(R.id.videoViewWelcome);
        mycontroller=new MediaController(this);
        mycontroller.setAnchorView(videoViewWelcome);
        videoViewWelcome.setMediaController(mycontroller);
        videoViewWelcome.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.startvideo));
        //Start video
        videoViewWelcome.start();

        //Navigation system
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen3_welcome.this, screen1_startgame.class);
                startActivity(in);
            }
        });

        botaoContinuar = findViewById(R.id.buttonCadastrart1);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen3_welcome.this, screen4_game1.class);
                startActivity(in);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        consultarUltimoNickname();
    }

    // Função para descriptografar os dados enviados
    public String descriptografarDados(String dadosCriptografados, String chaveBase64,
                                       String ivBase64) {
        try {
            // Decodifica a chave e o IV de Base64 para bytes
            byte[] chave = Base64.decode(chaveBase64, Base64.DEFAULT);
            byte[] iv = Base64.decode(ivBase64, Base64.DEFAULT);

            // Exibe a chave e o IV em logs para depuração
            Log.d("Descriptografia get", new String(chave));
            Log.d("IV get", new String(iv));

            // Inicializa o objeto Cipher para descriptografia com o algoritmo AES/CBC/PKCS5PADDING
            Cipher decipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            decipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"),
                    new IvParameterSpec(iv));

            // Descriptografa os dados criptografados
            byte[] dadosDescriptografados = decipher.doFinal(Base64.decode(dadosCriptografados,
                    Base64.DEFAULT));

            // Converte os dados descriptografados de bytes para String
            String dadosDescriptografadosString = new String(dadosDescriptografados, "UTF-8");

            // Exibe os dados descriptografados em logs para depuração
            Log.d("Dados Descriptografados", dadosDescriptografadosString);

            // Retorna os dados descriptografados como uma String
            return dadosDescriptografadosString;
        } catch (Exception e) {
            // Em caso de erro, imprime o stack trace e retorna null
            e.printStackTrace();
            return null;
        }
    }

    // Rota GET para consultar o último nickname
    private void consultarUltimoNickname() {
        // URL da requisição GET para buscar o último nickname
        String url = "https://6xrrfz-3000.csb.app/ultimo-nickname";

        // Criação de uma requisição GET usando JsonObjectRequest
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Extrai os dados criptografados do nickname, IV e chave de
                            // descriptografia do JSON de resposta
                            String dadosCriptografadosNickname = response.getString(
                                    "nickname_criptografado");
                            String iv = response.getString("iv");
                            String chaveNickname = response.getString("chave_nickname");

                            // Descriptografa o nickname usando os dados recebidos
                            String dadosDescriptografadosNickname = descriptografarDados(
                                    dadosCriptografadosNickname, chaveNickname, iv
                            );

                            // Exibe o nickname descriptografado no textView2 se for bem-sucedido
                            if (dadosDescriptografadosNickname != null) {
                                textView2.setText(dadosDescriptografadosNickname);
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
                        // Exibe uma mensagem de erro no textView2 em caso de falha na requisição
                        textView2.setText("Erro ao consultar o último nickname.");
                    }
                });

        // Adiciona a requisição à fila de requisições
        requestQueue.add(request);
    }
}

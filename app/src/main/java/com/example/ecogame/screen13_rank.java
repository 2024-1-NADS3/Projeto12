package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class screen13_rank extends AppCompatActivity {

    private Button btnRank;
    TableLayout tabRank;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen13_rank);

        btnRank = findViewById(R.id.btnRank);
        tabRank = findViewById(R.id.tabRank);

        btnRank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), screen11_certificate.class);
                startActivity(in);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        consultarRank();
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

    private void consultarRank() {
        String url = "https://6xrrfz-3000.csb.app/rank";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<JSONObject> dataList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                dataList.add(response.getJSONObject(i));
                            }

                            Collections.sort(dataList, new Comparator<JSONObject>() {
                                @Override
                                public int compare(JSONObject o1, JSONObject o2) {
                                    try {
                                        int diferenca1 = o1.getInt("quantidade_verdadeiros_total") - o1.getInt("quantidade_falsos_total");
                                        int diferenca2 = o2.getInt("quantidade_verdadeiros_total") - o2.getInt("quantidade_falsos_total");
                                        return Integer.compare(diferenca2, diferenca1); // Ordenar do maior para o menor
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        return 0;
                                    }
                                }
                            });

                            exibirDadosOrdenados(dataList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TableRow row = new TableRow(screen13_rank.this);
                        TextView textView = new TextView(screen13_rank.this);
                        textView.setText("Erro ao consultar os dados.");
                        textView.setPadding(16, 16, 16, 16);
                        row.addView(textView);
                        tabRank.addView(row);
                    }
                });

        requestQueue.add(request);
    }

    private void exibirDadosOrdenados(List<JSONObject> dataList) {
        TableLayout tabRank = findViewById(R.id.tabRank);
        tabRank.removeAllViews();
        // Criação do cabeçalho da tabela
        TableRow headerRow = new TableRow(screen13_rank.this);

        TextView headerNickname = new TextView(screen13_rank.this);
        headerNickname.setText("Nickname");
        headerNickname.setPadding(16, 16, 20, 16);
        headerNickname.setTypeface(null, Typeface.BOLD); // Define o texto como negrito
        headerNickname.setTextSize(12); // Define o tamanho do texto como 12
        headerNickname.setTextColor(Color.WHITE); // Define a cor do texto como branco
        headerNickname.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal
        headerNickname.setGravity(Gravity.CENTER); // Centraliza o texto

        TextView headerVerdadeiros = new TextView(screen13_rank.this);
        headerVerdadeiros.setText("Acertos");
        headerVerdadeiros.setPadding(16, 16, 20, 16);
        headerVerdadeiros.setTypeface(null, Typeface.BOLD); // Define o texto como negrito
        headerVerdadeiros.setTextSize(12); // Define o tamanho do texto como 12
        headerVerdadeiros.setTextColor(Color.WHITE); // Define a cor do texto como branco
        headerVerdadeiros.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal
        headerVerdadeiros.setGravity(Gravity.CENTER); // Centraliza o texto

        TextView headerFalsos = new TextView(screen13_rank.this);
        headerFalsos.setText("Falhas");
        headerFalsos.setPadding(16, 16, 20, 16);
        headerFalsos.setTypeface(null, Typeface.BOLD); // Define o texto como negrito
        headerFalsos.setTextSize(12); // Define o tamanho do texto como 12
        headerFalsos.setTextColor(Color.WHITE); // Define a cor do texto como branco
        headerFalsos.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal
        headerFalsos.setGravity(Gravity.CENTER); // Centraliza o texto

        TextView headerDiferenca = new TextView(screen13_rank.this);
        headerDiferenca.setText("Pontuação Final");
        headerDiferenca.setPadding(16, 16, 20, 16);
        headerDiferenca.setTypeface(null, Typeface.BOLD); // Define o texto como negrito
        headerDiferenca.setTextSize(12); // Define o tamanho do texto como 12
        headerDiferenca.setTextColor(Color.WHITE); // Define a cor do texto como branco
        headerDiferenca.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal
        headerDiferenca.setGravity(Gravity.CENTER); // Centraliza o texto

        headerRow.addView(headerNickname);
        headerRow.addView(headerVerdadeiros);
        headerRow.addView(headerFalsos);
        headerRow.addView(headerDiferenca);

        tabRank.addView(headerRow);

        for (JSONObject dataObject : dataList) {
            try {
                String nickname = dataObject.getString("nickname");
                int quantidadeVerdadeiros = dataObject.getInt("quantidade_verdadeiros_total");
                int quantidadeFalsos = dataObject.getInt("quantidade_falsos_total");
                int diferenca = quantidadeVerdadeiros - quantidadeFalsos;

                TableRow dataRow = new TableRow(screen13_rank.this);

                TextView textViewNickname = new TextView(screen13_rank.this);
                textViewNickname.setText(nickname);
                textViewNickname.setPadding(16, 16, 20, 16);
                textViewNickname.setTextSize(12); // Define o tamanho do texto como 12
                textViewNickname.setTextColor(Color.WHITE); // Define a cor do texto como branco
                textViewNickname.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal

                TextView textViewVerdadeiros = new TextView(screen13_rank.this);
                textViewVerdadeiros.setText(String.valueOf(quantidadeVerdadeiros));
                textViewVerdadeiros.setPadding(16, 16, 20, 16);
                textViewVerdadeiros.setTextSize(12); // Define o tamanho do texto como 12
                textViewVerdadeiros.setTextColor(Color.WHITE); // Define a cor do texto como branco
                textViewVerdadeiros.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal

                TextView textViewFalsos = new TextView(screen13_rank.this);
                textViewFalsos.setText(String.valueOf(quantidadeFalsos));
                textViewFalsos.setPadding(16, 16, 20, 16);
                textViewFalsos.setTextSize(12); // Define o tamanho do texto como 12
                textViewFalsos.setTextColor(Color.WHITE); // Define a cor do texto como branco
                textViewFalsos.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal

                TextView textViewDiferenca = new TextView(screen13_rank.this);
                textViewDiferenca.setText(String.valueOf(diferenca));
                textViewDiferenca.setPadding(16, 16, 20, 16);
                textViewDiferenca.setTextSize(12); // Define o tamanho do texto como 12
                textViewDiferenca.setTextColor(Color.WHITE); // Define a cor do texto como branco
                textViewDiferenca.setBackgroundColor(Color.parseColor("#9F142A49")); // Define o background com o código hexadecimal

                dataRow.addView(textViewNickname);
                dataRow.addView(textViewVerdadeiros);
                dataRow.addView(textViewFalsos);
                dataRow.addView(textViewDiferenca);

                tabRank.addView(dataRow);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

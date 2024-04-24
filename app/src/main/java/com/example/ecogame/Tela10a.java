package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Tela10a extends AppCompatActivity {
    String url = "https://nnn5h2-3000.csb.app";
    RequestQueue filaRequest;
    private int userId = -1;
    Button buttonCadastrartT10a;
    EditText editTextT10NickName;
    EditText editTextT10NomeCompleto;
    String text3 = "CERTIFICADO";
    String text1 = "Agradecemos a: ";
    String text2 = "Por ter concluido o jogo sobre Gerenciamento de Resíduos Recicláveis, com " +
            "duração de 2 horas, da EcoIAGame.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela10a);

        consultarUltimoId();
        filaRequest = Volley.newRequestQueue(this);
        buttonCadastrartT10a = findViewById(R.id.buttonCadastrartT10a);
        editTextT10NickName = findViewById(R.id.editTextT10NickName);
        editTextT10NomeCompleto = findViewById(R.id.editTextT10NomeCompleto);

        buttonCadastrartT10a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = editTextT10NickName.getText().toString();
                String nomeCompleto = editTextT10NomeCompleto.getText().toString();

                atualizarUltimoIdCadastrado(userId,nickname,nomeCompleto);
                gerarPdf();
                Intent in = new Intent(Tela10a.this, Tela11.class);
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
                        // Tratar erros de comunicação com o servidor
                        Log.e("Error", error.toString());
                        Toast.makeText(getApplicationContext(), "Erro ao buscar o último ID " +
                                "cadastrado. Por favor, tente novamente.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adicionar a requisição à fila
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void atualizarUltimoIdCadastrado(final int id, final String nome,
                                            final String nickname) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id", id);
            jsonBody.put("nome", nome);
            jsonBody.put("nickname", nickname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.PUT, this.url + "/usuarios/" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tratar a resposta do servidor, se necessário
                        Log.d("Response", response.toString());
                        Toast.makeText(getApplicationContext(), "ID cadastrado atualizado com " +
                                        "sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Tratar erros de comunicação com o servidor
                Log.e("Error", error.toString());
                Toast.makeText(getApplicationContext(), "Erro ao atualizar ID cadastrado. Por " +
                        "favor, tente novamente.", Toast.LENGTH_SHORT).show();
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

    // Gerar certificado de conclusão
    public void gerarPdf() {
        PdfDocument documentoPdf = new PdfDocument();

        PdfDocument.PageInfo detalhesPagina =
                new PdfDocument.PageInfo.Builder(500, 600, 1).create();

        PdfDocument.Page novaPagina = documentoPdf.startPage(detalhesPagina);
        Canvas canvas = novaPagina.getCanvas();
        // cortexto
        Paint corDoTexto = new Paint();
        corDoTexto.setColor(Color.BLACK);

        // Configuração geral
        Paint geralTexto = new Paint();
        geralTexto.setColor(Color.BLACK);
        geralTexto.setTextSize(18);

        // Configuração do Paint para o texto do certificado
        Paint paintCertificado = new Paint();
        paintCertificado.setColor(Color.BLACK);
        paintCertificado.setTextSize(20);
        paintCertificado.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintCertificado.setTextAlign(Paint.Align.CENTER);

        // Configuração do Paint para o texto do nome
        Paint paintNome = new Paint();
        paintNome.setColor(Color.BLACK);
        paintNome.setTextSize(18);
        paintNome.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // Centralizar o texto do certificado
        int x = 500 / 2; // Posição central horizontal
        int y = 130; // Posição vertical

        // Desenhar o texto do certificado
        canvas.drawText(text3, x, y, paintCertificado);
        canvas.drawText(text1, 70, 200, geralTexto);
        canvas.drawText(editTextT10NomeCompleto.getText().toString(), 140, 240, paintNome);

        // Definir a largura máxima para a quebra automática do texto
        int larguraMaxima = 400;
        // Criar um objeto TextPaint para o StaticLayout
        TextPaint staticLayoutPaint = new TextPaint(corDoTexto);
        // Definir o tamanho do texto no TextPaint
        staticLayoutPaint.setTextSize(16);
        // Criar um objeto StaticLayout para lidar com a quebra automática do texto
        StaticLayout staticLayout = new StaticLayout(text2, staticLayoutPaint, larguraMaxima,
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        // Desenhar o texto com quebra automática
        canvas.save();
        canvas.translate(70, 260); // Posição inicial do texto
        staticLayout.draw(canvas);
        canvas.restore();

        // Obter a imagem diretamente do drawable
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.logotransparente, null);
        Bitmap imageBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(imageBitmap);
        drawable.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
        drawable.draw(canvas2);

        // Definir o tamanho da imagem
        int width = 150; // Largura desejada
        int height = 150; // Altura desejada
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, true);

        // Desenhar a imagem no PDF
        canvas.drawBitmap(scaledBitmap, 180, 350, paintNome);

        documentoPdf.finishPage(novaPagina);

        //SDCard
        String targetPdf = "/sdcard/download/certificadoEcoIA.pdf";
        File filePath = new File(targetPdf);

        try {
            documentoPdf.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF gerado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {

            e.printStackTrace();
            Toast.makeText(this, "Falha ao gerar pdf" + e.toString(), Toast.LENGTH_LONG).show();

        }
        documentoPdf.close();
    }
}
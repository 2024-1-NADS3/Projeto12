package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class screen11_certificate extends AppCompatActivity {
    Button botaoRestart;
    Button buttonCertificated;
    String text3 = "CERTIFICADO";
    String text1 = "Parabéns por ter concluído esta jornada ao nosso lado! Agora você é " +
            "oficialmente um agente da natureza, pronto para fazer a diferença e ajudar a " +
            "proteger nosso planeta. Continue aplicando tudo que aprendeu e juntos faremos do " +
            "mundo um lugar melhor!";
    String text2 = "Você está recebendo seu certificado por ter concluido o jogo sobre " +
            "Gerenciamento de Resíduos Recicláveis, com " +
            "duração de 1 hora, da EcoIAGame.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen11_certificate);

        botaoRestart = findViewById(R.id.buttonRestart);
        buttonCertificated = findViewById(R.id.buttonCertificated);
        botaoRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen11_certificate.this, screen1_startlogin.class);
                startActivity(in);
            }
        });
        buttonCertificated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerarPdf();
                Intent in = new Intent(screen11_certificate.this, screen12_endgame.class);
                startActivity(in);
            }
        });
    }

    // Gerar certificado de conclusão
    public void gerarPdf() {
        PdfDocument documentoPdf = new PdfDocument();

        PdfDocument.PageInfo detalhesPagina =
                new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // Tamanho A4 em pixels (210mm x 297mm)

        PdfDocument.Page novaPagina = documentoPdf.startPage(detalhesPagina);
        Canvas canvas = novaPagina.getCanvas();

        // Configuração do Paint para as bordas
        Paint paintBorda = new Paint();
        paintBorda.setColor(Color.GREEN);
        paintBorda.setStyle(Paint.Style.STROKE);
        paintBorda.setStrokeWidth(10);
        canvas.drawRect(5, 5, detalhesPagina.getPageWidth() - 5, detalhesPagina.getPageHeight() - 5, paintBorda);

        // Configuração do Paint para o texto do certificado
        Paint paintCertificado = new Paint();
        paintCertificado.setColor(Color.BLACK);
        paintCertificado.setTextSize(20);
        paintCertificado.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintCertificado.setTextAlign(Paint.Align.CENTER);

        // Centralizar o texto do certificado
        int x = detalhesPagina.getPageWidth() / 2; // Posição central horizontal
        int y = 130; // Posição vertical

        // Desenhar o texto do certificado (text3)
        canvas.drawText(text3, x, y, paintCertificado);
        y += 30; // Espaço entre text3 e text1

        // Quebra automática para o texto1
        TextPaint text1Paint = new TextPaint();
        text1Paint.setColor(Color.BLACK);
        text1Paint.setTextSize(18);
        StaticLayout text1Layout = new StaticLayout(text1, text1Paint, detalhesPagina.getPageWidth() - 100,
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(50, y); // Posição inicial do texto
        text1Layout.draw(canvas);
        canvas.restore();
        y += text1Layout.getHeight() + 20; // Espaço entre text1 e text2

        // Quebra automática para o texto2
        TextPaint text2Paint = new TextPaint();
        text2Paint.setColor(Color.BLACK);
        text2Paint.setTextSize(20); // Tamanho de fonte igual ao text3
        StaticLayout text2Layout = new StaticLayout(text2, text2Paint, detalhesPagina.getPageWidth() - 100,
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(50, y); // Posição inicial do texto
        text2Layout.draw(canvas);
        canvas.restore();
        y += text2Layout.getHeight() + 20; // Espaço entre text2 e logo

        // Obter a imagem diretamente do drawable
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.logotransparente);
        Bitmap imageBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(imageBitmap);
        drawable.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
        drawable.draw(canvas2);

        // Definir o tamanho da imagem
        int width = 150; // Largura desejada
        int height = 150; // Altura desejada
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, true);

        // Desenhar o logo centralizado abaixo do Text2
        int logoX = detalhesPagina.getPageWidth() / 2 - width / 2; // Posição central horizontal
        int logoY = y + 20; // Posição vertical com um espaço adicional
        canvas.drawBitmap(scaledBitmap, logoX, logoY, null);

        y += height + 40; // Espaço para a data

        // Configuração do Paint para a data
        Paint paintData = new Paint();
        paintData.setColor(Color.BLACK);
        paintData.setTextSize(16);
        paintData.setTextAlign(Paint.Align.CENTER);

        // Desenhar a data centralizada abaixo do logo
        canvas.drawText("Data: " + getCurrentDate(), detalhesPagina.getPageWidth() / 2, y, paintData);

        documentoPdf.finishPage(novaPagina);

        //SDCard
        String targetPdf = Environment.getExternalStorageDirectory().getPath() + "/download/certificadoEcoIAGame.pdf";
        File filePath = new File(targetPdf);

        try {
            documentoPdf.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF gerado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Falha ao gerar PDF: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        documentoPdf.close();
    }

    // Método para obter a data atual
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}
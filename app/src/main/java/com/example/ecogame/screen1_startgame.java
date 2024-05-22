package com.example.ecogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class screen1_startgame extends AppCompatActivity {

    RequestQueue filaRequest;
    String[] nomesPrecadastrados = {"Coelho Cenoura",
            "Gato Bigodes",
            "Cachorro Patinhas",
            "Peixe Bolha",
            "Papagaio Papo",
            "Hamster Roda",
            "Tartaruga Casca",
            "Panda Bambu",
            "Leão Juba",
            "Borboleta Asas"};

    String url = "https://6xrrfz-3000.csb.app/usuarios1";
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1_startgame);

        //Navegaçãp
        botao = findViewById(R.id.buttonStart);
        filaRequest = Volley.newRequestQueue(this);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gerar um nome aleatório com base na lista de nomes pré-cadastrados
                String nome = gerarNomeAleatorio(nomesPrecadastrados);
                // Gerar um nickname aleatório com base na lista de nomes pré-cadastrados
                String nickname = gerarNicknameAleatorio(nomesPrecadastrados);

                if (!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(nickname)) {
                    enviarDadosCriptografadosParaServidor(nome, nickname);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent in = new Intent(getApplicationContext(), screen3_welcome.class);
                            startActivity(in);
                        }
                    }, 2000); // Atraso de 1 segundo (1000 milissegundos)
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos" +
                            ".", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Gerar Nickname Automático
    private String gerarNicknameAleatorio(String[] nomesPrecadastrados) {
        // Gerar um índice aleatório para selecionar um nome da lista
        Random random = new Random();
        int indiceAleatorio = random.nextInt(nomesPrecadastrados.length);

        // Selecionar um nome aleatório da lista
        String nomeAleatorio = nomesPrecadastrados[indiceAleatorio];

        // Gerar um nickname com base nas iniciais do nome selecionado
        String[] partesNome = nomeAleatorio.split(" ");
        StringBuilder nicknameBuilder = new StringBuilder();
        for (String parte : partesNome) {
            nicknameBuilder.append(parte.charAt(0));
        }

        // Limitar o nickname entre 6 e 10 caracteres
        int tamanhoNickname = nicknameBuilder.toString().length();
        if (tamanhoNickname < 6) {
            // Adicionar caracteres aleatórios para atingir o mínimo de 6 caracteres
            while (nicknameBuilder.length() < 6) {
                nicknameBuilder.append((char) (random.nextInt(26) + 'a'));
            }
        } else if (tamanhoNickname > 10) {
            // Remover caracteres aleatórios para atingir o máximo de 10 caracteres
            nicknameBuilder.setLength(10);
        }

        return nicknameBuilder.toString();
    }

    //Gerar Nome Automático
    private String gerarNomeAleatorio(String[] nomesPrecadastrados) {
        // Gerar um índice aleatório para selecionar um nome da lista
        Random random = new Random();
        int indiceAleatorio = random.nextInt(nomesPrecadastrados.length);

        // Selecionar um nome aleatório da lista
        String nomeAleatorio = nomesPrecadastrados[indiceAleatorio];

        // Limitar o nome a 20 caracteres
        return nomeAleatorio.substring(0, Math.min(20, nomeAleatorio.length()));
    }


    //Vetor de inicialização
    private byte[] gerarIVAleatorio() {
        byte[] iv = new byte[16]; // Gerando IV de 16 bytes
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    //Hash (SHA-256) para gerar uma chave aleatória para criptografar o nome antes de enviá-lo
    // para o servidor
    private String gerarChaveNome(String dados) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(dados.getBytes(StandardCharsets.UTF_8));
            byte[] chave = Arrays.copyOf(hash, 32); // Ajusta a chave para 32 bytes
            return Base64.encodeToString(chave, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Hash (SHA-256) para gerar uma chave aleatória para criptografar o nickname antes de
    // enviá-lo para o servidor
    private String gerarChaveNickname(String dados) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(dados.getBytes(StandardCharsets.UTF_8));
            byte[] chave = Arrays.copyOf(hash, 32); // Ajusta a chave para 32 bytes
            return Base64.encodeToString(chave, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Cadastrar o usuário no banco de dados com dados criptografados
    public void enviarDadosCriptografadosParaServidor(final String nome, final String nickname) {
        // Gera a chave de criptografia para o nome
        String chaveNome = gerarChaveNome(nome);
        Log.d("Android", "Teste nome: " + chaveNome);

        // Gera a chave de criptografia para o nickname
        String chaveNickname = gerarChaveNickname(nickname);
        Log.d("Android", "Teste nickname: " + chaveNickname);

        // Gera um IV (Initialization Vector) aleatório
        byte[] iv = gerarIVAleatorio();

        // Criptografa o nome e o nickname usando as chaves e o IV gerados
        String dadosCriptografadosNome = criptografarNome(nome, chaveNome, iv);
        String dadosCriptografadosNickname = criptografarNickname(nickname, chaveNickname, iv);

        // Exibe os dados criptografados no log
        Log.d("Android", "Dados criptografados nome: " + dadosCriptografadosNome);
        Log.d("Android", "Dados criptografados nickname: " + dadosCriptografadosNickname);

        // Cria um objeto JSONObject para enviar os dados criptografados para o servidor
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("nome_criptografado", dadosCriptografadosNome);
            requestBody.put("nickname_criptografado", dadosCriptografadosNickname);
            requestBody.put("chave_nome", chaveNome);
            requestBody.put("chave_nickname", chaveNickname);
            requestBody.put("iv", Base64.encodeToString(iv, Base64.DEFAULT));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Tratar a resposta do servidor, se necessário
                        Log.d("Android", "Resposta do servidor: " + response);
                        Toast.makeText(getApplicationContext(), "Dados cadastrados com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Tratar erros de comunicação com o servidor
                Log.e("Error", error.toString());
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar dados. Por favor, " +
                        "tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody.toString().getBytes(StandardCharsets.UTF_8);
            }
        };

        filaRequest.add(request);
    }

    // Criptografar o nome
    private String criptografarNome(String nome, String chaveNome, byte[] iv) {
        try {
            // Obtém uma instância do Cipher para realizar a criptografia
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Decodifica a chave do nome (String base64) para um array de bytes
            SecretKeySpec keySpec = new SecretKeySpec(Base64.decode(chaveNome, Base64.DEFAULT),
                    "AES");

            // Cria um IvParameterSpec usando o IV fornecido
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Inicializa o cipher no modo de criptografia (ENCRYPT_MODE) usando a chave e o IV
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            // Converte o nome (String) para bytes usando o padrão de codificação UTF-8
            byte[] nomeBytes = nome.getBytes(StandardCharsets.UTF_8);

            // Executa a criptografia usando o cipher e obtém os dados criptografados
            byte[] dadosCriptografadosBytes = cipher.doFinal(nomeBytes);

            // Codifica os dados criptografados em uma String base64 e retorna
            return Base64.encodeToString(dadosCriptografadosBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // Trata qualquer exceção que possa ocorrer durante a criptografia
            e.printStackTrace();
            return null;
        }
    }

    // Criptografar o nickname
    private String criptografarNickname(String nickname, String chaveNickname, byte[] iv) {
        try {
            // Obtém uma instância do Cipher para realizar a criptografia
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Decodifica a chave do nickname (String base64) para um array de bytes
            SecretKeySpec keySpec = new SecretKeySpec(Base64.decode(chaveNickname,
                    Base64.DEFAULT), "AES");

            // Cria um IvParameterSpec usando o IV fornecido
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Inicializa o cipher no modo de criptografia (ENCRYPT_MODE) usando a chave e o IV
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            // Converte o nickname (String) para bytes usando o padrão de codificação UTF-8
            byte[] nicknameBytes = nickname.getBytes(StandardCharsets.UTF_8);

            // Executa a criptografia usando o cipher e obtém os dados criptografados
            byte[] dadosCriptografadosBytes = cipher.doFinal(nicknameBytes);

            // Codifica os dados criptografados em uma String base64 e retorna
            return Base64.encodeToString(dadosCriptografadosBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // Trata qualquer exceção que possa ocorrer durante a criptografia
            e.printStackTrace();
            return null;
        }
    }
}
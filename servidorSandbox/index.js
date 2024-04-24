/*
 *@description Importações e configurações
 *@version 1.0.0
 *@author Grupo 12
 */
const express = require("express");
const bodyParser = require("body-parser");
const sqlite3 = require("sqlite3").verbose();

const app = express();
const port = process.env.PORT || 3000; // configuração da porta no servidor

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Criar o banco de dados, caso não exista
const db = new sqlite3.Database("myapp.db", (err) => {
  if (err) {
    return console.error(err.message);
  }
  console.log('Conectado ao banco de dados SQLite "myapp.db"');

  /*
   *@description Criar a tabela "cores" caso ela não exista
   */
  db.run(
    "CREATE TABLE IF NOT EXISTS cores (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, " +
      "cor_a INTEGER, cor_b INTEGER, cor_c INTEGER, cor_d INTEGER, cor_e INTEGER, cor_f INTEGER, cor_g INTEGER, cor_h INTEGER, cor_i INTEGER, cor_j INTEGER, cor_k INTEGER, cor_l INTEGER)",
    () => {
      /*
       *@description Criar a tabela "nomes" caso ela não exista
       */
      db.run(
        "CREATE TABLE IF NOT EXISTS nomes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, nickname TEXT NOT NULL, " +
          "FOREIGN KEY(id) REFERENCES cores(user_id))",
        (err) => {
          if (err) {
            console.error(err.message);
          } else {
            console.log("Chave estrangeira criada com sucesso");
          }
        },
      );
    },
  );
});

/*
 *=====================================Metodos Tabela Nomes - CRUD =========================================
 */
/*
 *@description Cadastrar usuario
 */
app.post("/usuarios1", (req, res) => {
  const { nome, nickname } = req.body;
  const sql = "INSERT INTO nomes (nome, nickname) VALUES (?, ?)";
  const params = [nome, nickname];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.status(201).json({ id: this.lastID, nome, nickname });
  });
});

/*
 *@description Pegar todos os dados cadastrados na tabela
 */
app.get("/usuarios", (req, res) => {
  const sql = "SELECT * FROM nomes";

  db.all(sql, [], (err, rows) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.status(200).json(rows);
  });
});

/*
 *@description Pesquisar usuarios cadastrados pelo id
 */
app.get("/usuarios/:id", (req, res) => {
  const { id } = req.params;
  const sql = `SELECT * FROM nomes WHERE id = ${id}`;

  db.get(sql, [], (err, row) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (!row) {
      return res.status(404).json({ error: "Nome não encontrado" });
    }
    res.status(200).json(row);
  });
});

/*
 *@descriptionPesquisar o último ID cadastrado na tabela "nomes"
 */
app.get("/ultimo-id", (req, res) => {
  const sql = "SELECT id FROM nomes ORDER BY id DESC LIMIT 1";

  db.get(sql, [], (err, row) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (!row) {
      return res.status(200).json({ id: null });
    }
    res.status(200).json({ id: row.id });
  });
});

/*
 *@description Pesquisar um usuário(nickname) pelo último id cadastrado
 */
app.get("/ultimo-nickname", (req, res) => {
  const sql = "SELECT nickname FROM nomes ORDER BY id DESC LIMIT 1";

  db.get(sql, [], (err, row) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (!row) {
      return res.status(200).json({ nickname: null });
    }
    res.status(200).json({ nickname: row.nickname });
  });
});

/*
 *@description Pesquisar um usuário(nome) pelo último id cadastrado
 */
app.get("/ultimo-nome", (req, res) => {
  const sql = "SELECT nome FROM nomes ORDER BY id DESC LIMIT 1";

  db.get(sql, [], (err, row) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (!row) {
      return res.status(200).json({ nome: null });
    }
    res.status(200).json({ nome: row.nome });
  });
});

/*
 *@descriptionAtualizar um usuário
 */
app.put("/usuarios/:id", (req, res) => {
  const { id } = req.params;
  const { nome, nickname } = req.body;
  const sql = `UPDATE nomes SET nome = ?, nickname = ? WHERE id = ?`;
  const params = [nome, nickname, id];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (this.changes === 0) {
      return res.status(404).json({ error: "Nome não encontrado" });
    }
    res.status(200).json({ id, nome, nickname });
  });
});

/*
 *@description Deletar um usuário
 */
app.delete("/usuarios/:id", (req, res) => {
  const { id } = req.params;
  const sql = `DELETE FROM nomes WHERE id = ?`;
  const params = [id];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (this.changes === 0) {
      return res.status(404).json({ error: "Nome não encontrado" });
    }
    res.status(204).send();
  });
});

/*=========================================== Metodos Tabela Cores - CRUD =========================================*/
/*
 *@description Cadastrar cor
 */
app.post("/usuarios/:id/cores", (req, res) => {
  const { id } = req.params;
  const {
    cor_a,
    cor_b,
    cor_c,
    cor_d,
    cor_e,
    cor_f,
    cor_g,
    cor_h,
    cor_i,
    cor_j,
    cor_k,
    cor_l,
  } = req.body;
  const sql =
    "INSERT INTO cores (user_id, cor_a, cor_b, cor_c, cor_d, cor_e, cor_f, cor_g, cor_h, cor_i, cor_j, cor_k, cor_l) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  const params = [
    id,
    cor_a,
    cor_b,
    cor_c,
    cor_d,
    cor_e,
    cor_f,
    cor_g,
    cor_h,
    cor_i,
    cor_j,
    cor_k,
    cor_l,
  ];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.status(201).json({
      id: this.lastID,
      cor_a,
      cor_b,
      cor_c,
      cor_d,
      cor_e,
      cor_f,
      cor_g,
      cor_h,
      cor_i,
      cor_j,
      cor_k,
      cor_l,
    });
  });
});

/*
 *@description Pegar todos os dados cadastrados na tabela "cores"
 */
app.get("/cores", (req, res) => {
  const sql = "SELECT * FROM cores";

  db.all(sql, [], (err, rows) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.status(200).json(rows);
  });
});

/*
 *@description Pesquisar cores cadastradas pelo id do usuário
 */
app.get("/cores/:id", (req, res) => {
  const { id } = req.params;
  const sql = `SELECT * FROM cores WHERE user_id = ${id}`;

  db.all(sql, [], (err, rows) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.status(200).json(rows);
  });
});

/*
 *@description Atualizar as cores de um usuário
 */
app.put("/usuarios/:id/cores", (req, res) => {
  const { id } = req.params;
  const {
    cor_a,
    cor_b,
    cor_c,
    cor_d,
    cor_e,
    cor_f,
    cor_g,
    cor_h,
    cor_i,
    cor_j,
    cor_k,
    cor_l,
  } = req.body;
  const sql = `UPDATE cores SET cor_a = ?, cor_b = ?, cor_c = ?, cor_d = ?, cor_e = ?, cor_f = ?, cor_g = ?, cor_h = ?, cor_i = ?, cor_j = ?, cor_k = ?, cor_l = ? WHERE user_id = ?`;
  const params = [
    cor_a,
    cor_b,
    cor_c,
    cor_d,
    cor_e,
    cor_f,
    cor_g,
    cor_h,
    cor_i,
    cor_j,
    cor_k,
    cor_l,
    id,
  ];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (this.changes === 0) {
      return res.status(404).json({ error: "Cores não encontradas" });
    }
    res.status(200).json({
      id,
      cor_a,
      cor_b,
      cor_c,
      cor_d,
      cor_e,
      cor_f,
      cor_g,
      cor_h,
      cor_i,
      cor_j,
      cor_k,
      cor_l,
    });
  });
});

/*
 *@description  Deletar as cores de um usuário
 */
app.delete("/usuarios/:id/cores", (req, res) => {
  const { id } = req.params;
  const sql = `DELETE FROM cores WHERE user_id = ?`;
  const params = [id];

  db.run(sql, params, function (err) {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (this.changes === 0) {
      return res.status(404).json({ error: "Cores não encontradas" });
    }
    res.status(204).send();
  });
});

/*
 *@description Pegar a última cor cadastrada
 */
app.get("/ultima-cor", (req, res) => {
  const sql = "SELECT * FROM cores ORDER BY id DESC LIMIT 1";

  db.get(sql, [], (err, row) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (!row) {
      return res.status(200).json({
        cor_a: null,
        cor_b: null,
        cor_c: null,
        cor_d: null,
        cor_e: null,
        cor_f: null,
        cor_g: null,
        cor_h: null,
        cor_i: null,
        cor_j: null,
        cor_k: null,
        cor_l: null,
      });
    }
    res.status(200).json({
      cor_a: row.cor_a,
      cor_b: row.cor_b,
      cor_c: row.cor_c,
      cor_d: row.cor_d,
      cor_e: row.cor_e,
      cor_f: row.cor_f,
      cor_g: row.cor_g,
      cor_h: row.cor_h,
      cor_i: row.cor_i,
      cor_j: row.cor_j,
      cor_k: row.cor_k,
      cor_l: row.cor_l,
    });
  });
});

/*
 *@description Retornar a porta que o servidor está rodando no console
 */
app.listen(port, () => {
  console.log(`Servidor rodando em http://localhost:${port}`);
});

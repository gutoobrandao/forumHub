# Alura Fórum Hub - Challenge ONE

Projeto desenvolvido como desafio da **Alura**, com backend em **Java** e **Spring Boot**, utilizando **Spring Security** e autenticação via **JWT**. O sistema permite gerenciar usuários, cursos, tópicos e respostas em um fórum.

---

## Configuração do banco de dados

Crie um arquivo `.env` na raiz do projeto (mesma pasta do `pom.xml`) e configure suas variáveis de ambiente:

`FH_DB_URL="jdbc:mysql://localhost:3306/nomedobancodedados?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" FH_DB_USERNAME="seuUsernameAqui" FH_DB_PASSWORD="suaSenhaAqui"`

Para que o IntelliJ reconheça o `.env` ao rodar o projeto:

1. Vá em **Run > Edit Configurations**.
2. No campo **Environment Variables**, clique em **Selecionar pasta** e escolha o arquivo `.env`.

Pronto! Agora o projeto consegue acessar suas variáveis de ambiente.

---

## Autenticação

O projeto utiliza **Spring Security** com **JWT** de 8 horas de duração.

- Os endpoints de **cadastro** e **login** são públicos.
- Todos os outros endpoints exigem token JWT no **Bearer Token**.

### Cadastro de usuário

Endpoint: `POST /usuarios/cadastro`

Exemplo de JSON:

```json
{
  "nome": "Alberto Albatroz",
  "email": "albertin1958@uainet.com",
  "senha": "12345678"
}
```

Resposta esperada: **201 Created** + JSON com os dados do usuário.

### Login

Endpoint: `POST /usuarios/login`

Exemplo de JSON:

```json
{
  "email": "albertin1958@uainet.com",
  "senha": "12345678"
}
```

Resposta esperada: **201 Created** + JSON com o **token JWT**.

---

## Usuário

### Detalhar

`GET /usuarios/{id}`

- Retorna **200 OK** + JSON com os dados do usuário.

### Listar

`GET /usuarios`

- Retorna **200 OK** + lista de usuários paginada (10 por página, ordem alfabética por nome).

### Atualizar

`PUT /usuarios`

- Apenas o usuário logado pode atualizar seus próprios dados.
- JSON opcional com campos a alterar:

```json
{
  "nome": "Alberto Albatroz",
  "email": "albertin1958@uainet.com",
  "senha": "1234"
}
```

- Retorno: **200 OK** + JSON atualizado.

### Deletar

`DELETE /usuarios/{id}`

- Apenas o usuário logado pode deletar sua conta.
- Retorno: **204 No Content**

---

## Curso

### Cadastrar

`POST /cursos`

Exemplo de JSON:

```json
​{
  "nome": "Como ser Dev Supremo em 1 mês",
  "categoria": "Desenvolvimento"
}
```

- Categorias disponíveis: Desenvolvimento, Frontend, Backend, Marketing, Inovação, Gestão e UX/UI.
- Retorno: **201 Created** + JSON do curso criado.

### Detalhar

`GET /cursos/{id}`

- Retorno: **200 OK** + JSON com dados do curso.

### Listar

`GET /cursos`

- Lista cursos paginados (10 por página, ordem alfabética).
- Retorno: **200 OK**

### Atualizar

`PUT /cursos/{id}`

Exemplo de JSON opcional:

```json
{
  "nome": "SpaceDev: programando para as estrelas",
  "categoria": "Desenvolvimento"
}
```

- Retorno: **200 OK** + JSON atualizado.

### Deletar

`DELETE /cursos/{id}`

- Qualquer usuário pode deletar cursos na implementação atual.
- Retorno: **204 No Content**

---

## Tópico

### Cadastrar

`POST /topicos`

Exemplo de JSON:

```json
{
  "titulo": "Como desenvolver um fórum em Java e Spring?",
  "mensagem": "me ajuda",
  "cursoId": 1 
}
```

- O tópico será vinculado ao usuário logado.
- Retorno: **201 Created** + JSON do tópico criado.

### Detalhar

`GET /topicos/{id}`

- Retorno: **200 OK** + JSON do tópico.

### Listar

`GET /topicos`

- Lista tópicos paginados (10 por página, ordem ascendente por data de criação).
- Retorno: **200 OK**    

### Atualizar

`PUT /topicos/{id}`

- Apenas o autor do tópico pode atualizar.
- JSON obrigatório:

```json
{
  "titulo": "Cansei de desenvolver o forum",
  "mensagem": "Deixa quieto",
  "cursoId": 1 
}
```

- Retorno: **200 OK** + JSON atualizado.

### Deletar

`DELETE /topicos/{id}`

- Apenas o autor do tópico pode deletar.
- Retorno: **204 No Content**

---

## Resposta

### Cadastrar

`POST /respostas`

Exemplo de JSON:

```json
{
  "topicoId": 1,
  "mensagem": "Faça o curso ONE de novo." 
}
```

- A resposta será vinculada ao usuário logado.
- Retorno: **201 Created** + JSON da resposta criada.

### Detalhar

`GET /respostas/{id}`

- Retorno: **200 OK** + JSON da resposta.

### Listar

`GET /respostas`

- Lista respostas paginadas (10 por página, ordem ascendente pelo id do tópico).
- Retorno: **200 OK**

### Atualizar

`PUT /respostas/{id}`

- Apenas o autor da resposta pode atualizar.
- JSON obrigatório:

```json
{
  "topicoId": 1,
  "mensagem": "Se precisar faça o curso ONE várias vezes."
}
```

- Retorno: **200 OK** + JSON atualizado.

### Deletar

`DELETE /respostas/{id}`

- Apenas o autor da resposta pode deletar.
- Retorno: **204 No Content**

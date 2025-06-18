# 🔗 Tinye.me - Backend

API REST do projeto [Tinye.me](https://tinye.me), um encurtador de links com autenticação de usuário, redirecionamento e painel de gerenciamento.

Este repositório contém o backend desenvolvido com **Java + Spring Boot**, responsável por autenticação, geração de links curtos e comunicação com o banco de dados PostgreSQL.

---

## [Link do repositório do Front-end](https://github.com/h9nrique/tinye-front)

---

## 🚀 Funcionalidades

- Cadastro e login de usuários
- Geração de links curtos
- Redirecionamento
- Listagem e remoção de links
- Comunicação com banco de dados PostgreSQL
- Validação de dados e tratamento de erros
- Integração com o frontend (Next.js)

---

## 🛠️ Tecnologias

- **Java 24**
- **PostgreSQL**
- **Lombok** – para reduzir código boilerplate
- **Auth0 - java-jwt** – geração e validação de tokens JWT
- **Apache Commons Lang** – utilitários para manipulação de strings, objetos, etc.
- **JPA / Hibernate**
- **Maven**

---

## 📬 Endpoints da API

### 🔐 Autenticação (`/api/v1/auth`)

| Método | Rota         | Descrição                      | Body (`application/json`)         |
|--------|--------------|--------------------------------|-----------------------------------|
| POST   | `/login`     | Realiza login e gera JWT       | `{ "email": "user@mail.com", "password": "senha" }` |
| POST   | `/register`  | Cria uma nova conta de usuário | `{ "name": "Usuário", "email": "user@mail.com", "password": "senha" }` |


### 🔗 Links (`/api/v1/link`)

> ⚠️ Maioria das rotas abaixo requerem autenticação com JWT no header
> ✅ **Exceção:** o `POST /` **não exige autenticação**, mas se o token for enviado, o link será associado ao usuário autenticado.  
> `Authorization: Bearer SEU_TOKEN`

| Método   | Rota                | Descrição                             | Body / Parâmetro                |
|----------|---------------------|----------------------------------------|----------------------------------|
| POST     | `/`                 | Cria um novo link curto                | `{ "url": "https://exemplo.com" }` |
| GET      | `/{shortLink}`      | Retorna a URL original do link curto   | `shortLink` (string)             |
| DELETE   | `/{id}`             | Deleta um link do usuário              | `id` (UUID)                      |
| GET      | `/links`           | Lista todos os links do usuário        | —                                |
| PATCH    | `/status/{id}`      | Ativa ou desativa o link               | `id` (UUID)                      |

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 24
- PostgreSQL
- Maven

### Passo a passo

```bash
# Clone o repositório
git clone https://github.com/h9nrique/tinye-backend.git
cd tinye-backend

# Crie um banco de dados no PostgreSQL
# Exemplo: tinye

# Configure o arquivo application.properties ou application.yml

spring.application.name=shortener
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/tinye}
spring.datasource.username=${DB_USERNAME:admin}
spring.datasource.password=${DB_PASSWORD:admin}

api.security.token.secret=${JWT_SECRET}

```

👨‍💻 Autor
Bruno Henrique Pereira  
[LinkedIn](https://www.linkedin.com/in/h9nrique/) • [GitHub](https://github.com/h9nrique) • [Portfólio](https://h9nrique.com.br/pt-BR)

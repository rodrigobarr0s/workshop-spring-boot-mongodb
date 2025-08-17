# Spring Boot 3 MongoDB  
[![License](https://img.shields.io/npm/l/react)](https://github.com/rodrigobarr0s/workshop-spring-boot-mongodb/blob/main/LICENSE)

## Sobre o projeto

Este repositório contém o backend de uma aplicação desenvolvida com **Spring Boot 3** e **MongoDB**, estruturada em camadas organizadas para uma API RESTful robusta. Inclui carga inicial de dados, uso de DTOs, relacionamentos entre documentos e consultas avançadas com operadores MongoDB.

## Arquitetura em Camadas

A aplicação segue um padrão de camadas bem definido:

![Arquitetura em Camadas](https://raw.githubusercontent.com/rodrigobarr0s/workshop-spring-boot-mongodb/refs/heads/main/src/main/resources/static/img/imagem-camadas.png)


## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data MongoDB**
- **MongoDB**
- **Maven**
- **MongoDB Compass**

## Modelo Conceitual

O modelo abaixo representa as principais entidades e relacionamentos da aplicação:

- `User`: representa um usuário do sistema
- `Post`: representa uma publicação feita por um usuário
- `CommentDTO`: representa comentários associados a um post
- `AuthorDTO`: projeção dos dados do autor

Relacionamento:  
- Um `User` pode ter vários `Post`  
- Um `Post` possui um autor (`AuthorDTO`) e uma lista de comentários (`CommentDTO`)

![Modelo de Entidades](https://raw.githubusercontent.com/rodrigobarr0s/workshop-spring-boot-mongodb/refs/heads/main/src/main/resources/static/img/imagem-entidades.png)

## Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+
- MongoDB instalado e rodando localmente (`mongod`)
- IDE de sua preferência (IntelliJ, Eclipse, VS Code...)

### Passos para execução local

```bash
# Clonar o repositório
git clone https://github.com/rodrigobarr0s/springboot3-mongodb.git

# Acessar o diretório do projeto
cd springboot3-mongodb

# Executar o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## Banco de Dados

A aplicação usa o banco **MongoDB local** com carga automática de dados iniciais.

### Configuração de conexão

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo
```

### Carga de dados (`Instantiation`)

Ao iniciar o projeto, o banco é populado com:

- Usuários (`User`)
- Posts (`Post`) com autor e comentários
- Relacionamento entre usuários e seus posts

## Estrutura do Projeto

- `domain/` → Entidades do MongoDB (`User`, `Post`)
- `dto/` → Objetos de transferência de dados (`UserDTO`, `AuthorDTO`, `CommentDTO`)
- `repository/` → Interfaces de persistência com Spring Data MongoDB
- `services/` → Camada de regras de negócio
- `resources/` → Endpoints da API REST
- `config/` → Carga de dados inicial (`CommandLineRunner`)
- `resources.util/` → Utilitários para decodificação de parâmetros e datas

## Endpoints Principais

### Usuários

- `GET /users` → Lista todos os usuários
- `GET /users/{id}` → Busca usuário por ID
- `POST /users` → Insere novo usuário
- `PUT /users/{id}` → Atualiza usuário
- `DELETE /users/{id}` → Remove usuário
- `GET /users/{id}/posts` → Lista os posts de um usuário

### Posts

- `GET /posts/{id}` → Busca post por ID
- `GET /posts/titlesearch?text=java` → Busca por título (case-insensitive)
- `GET /posts/fullsearch?text=java&minDate=2023-01-01&maxDate=2023-12-31` → Busca por texto em título, corpo ou comentários + intervalo de datas

## Consultas Avançadas

Utiliza o operador `$regex` para buscas flexíveis:

```java
@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
List<Post> searchTitle(String text);
```

```java
@Query("{ $and: [ 
    { $or: [ 
        { 'title': { $regex: ?0, $options: 'i' } }, 
        { 'body': { $regex: ?0, $options: 'i' } }, 
        { 'comments.text': { $regex: ?0, $options: 'i' } } 
    ] }, 
    { 'date': { $gte: ?1 } }, 
    { 'date': { $lte: ?2 } } 
] }")
List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
```

## Autor

**Rodrigo Barros**  
[LinkedIn](https://www.linkedin.com/in/rodrigobarr0s)

---
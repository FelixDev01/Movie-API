# 🎬 Movie API - Sistema de Catálogo de Filmes

Esta é uma API REST foi feita para gerenciamento de catálogos de filmes. O projeto foi construído utilizando **Spring Boot 3** e foca em padrões de design modernos, separação de responsabilidades e robustez técnica.

---

## 🛠️ Tecnologias e Conceitos Aplicados

- **Java 21 & Spring Boot 3**
- **Spring Data JPA:** Persistência de dados eficiente.
- **Camada de DTOs:** Uso de `RequestDTO` e `ResponseDTO` para desacoplar a API do modelo de dados (Entity).
- **Service Layer:** Regras de negócio isoladas e protegidas por transações (`@Transactional`).
- **Paginação Nativa:** Implementação de `Pageable` para performance em grandes volumes de dados.
- **Global Exception Handling:** Centralização de erros com `ControllerExceptionHandler`.
- **Enumeração de Domínio:** Gerenciamento rígido de categorias através do enum `Genero`.
- **Profiles de Configuração:** Diferenciação clara entre ambientes (Runtime vs Test) usando `application-test.properties`.

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de camadas para garantir manutenibilidade:

1. **Controller:** Exposição dos endpoints REST e recepção de parâmetros.
2. **Service:** Onde reside a inteligência da aplicação e conversão de objetos.
3. **Repository:** Interface de comunicação com o banco de dados.
4. **Model/Entity:** Representação fiel das tabelas do banco de dados.
5. **DTO:** Objetos leves para entrada e saída de dados, evitando exposição de IDs internos ou campos sensíveis.

---

## 🧪 Qualidade de Código: Testes Unitários

Implementei uma suite de testes utilizando **JUnit 5** e **Mockito** para validar a camada de serviço.
- **Mocks de Repositório:** Testes rápidos que não dependem de banco de dados.
- **Validação de Exceções:** Testes que garantem que o sistema lança `ResourceNotFoundException` corretamente.
- **Cobertura de CRUD:** Testes para persistência, busca por ID e exclusão lógica.

---
Gêneros Disponíveis: DRAMA, COMEDIA, ACAO, SUSPENSE, TERROR, AVENTURA.

⚙️ Como Rodar o Projeto
Certifique-se de ter o Java 17 e Maven instalados.

Clone o repositório:

Bash

git clone [https://github.com/FelixDev01/Movie-API.git](https://github.com/FelixDev01/Movie-API.git)
Execute o comando:

Bash

mvn spring-boot:run
O banco de dados H2 será populado automaticamente via import.sql para facilitar seus testes iniciais.

---

## 📖 Documentação da API

A API conta com documentação interativa via **Swagger/OpenAPI**.  
Com a aplicação rodando, acesse:  
`http://localhost:8080/swagger-ui.html`


### Exemplo de Payload (POST /filmes)
```json
{
  "titulo": "Interestelar",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "genero": "AVENTURA",
  "sinopse": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}


# Phone Recharges API
No contexto das telecomunicações o papel dos dispositivos móveis ocupa lugar de destaque, milhares de usuários diariamente realizam recargas nos seus dispositivos, tal processo que deve ser rápido e simples. O intuito do projeto é criar uma solução para dispositivos móveis de recargas de celular, com o foco na API desta solução. A solução foi implementada na linguagem Java, utilizando RESTful na construção da API.

## Funcionalidades
  - [x] **Cadastro de clientes**
    - Rotas: http://localhost:8080
      - Get: /clients | /clients/{id}
      - Post: /clients
      - Put: /clients/{id}
      - Delete: /clients/{id}
  - [x] **Cadastro de pagamentos**
    - Rotas: http://localhost:8080
      - Get: /payments | /payments/{id}
      - Post: /payments
      - Put: /payments/{id}
      - Delete: /payments/{id}
  - [x] **Realizar uma recarga**
    - Rotas: http://localhost:8080
      - Get: /recharges | /recharges/{id}
      - Post: /recharges
      - Put: /recharges/{id}
      - Delete: /recharges/{id}
  - [ ] Listar recargas anteriores do cliente

## Tecnologias
O projeto foi desenvolvido com as seguintes tecnologias:
  - [Java | -v 20.0.1](https://www.oracle.com/java/technologies/downloads/#java20)
  - [Spring Boot | -v 3.1.0](https://spring.io/projects/spring-boot)
  - [Spring Data JPA | -v 3.1.1](https://spring.io/projects/spring-data-jpa)
  - [H2 Database Engine](https://www.h2database.com/html/main.html)
  - [SpringDoc OpenAPI | -v 2.1.0](https://springdoc.org/v2/)
  - [Spring HATEOAS | -v -2.0.3](https://spring.io/projects/spring-hateoas)
  - [RabbitMQ](https://www.rabbitmq.com/)

## Pontos Importantes
Como o propósito dessa atividade é de exercício, abstraiu-se a segurança das interfaces, considerando todas as chamadas para essas interfaces como autorizadas, ou seja, todas as recargas serão aprovadas.
Foram implementadas duas API's, sendo a primeira para cadastro de cliente, pagamento e recarga. A segunda recebe a solicitação de recarga e a aprova, enviando uma mensagem para primeira para atualização de status.

As chamadas de testes à aplicação foram feitas utilizando o Postman e esporadicamente o CURL.

Como as duas aplicações (API's) deveriam estar em projetos separados, mas foram construídos apenas separados por pacotes, o arquivo pom.xml exigiu dois Profiles (perfis), `one` e `two`, para que os jars das aplicações fossem gerados corretamente. Os comandos usados para gerá-los, foram, respectivamente:
```bash
mvn clean
mvn package -P one
mvn package -P two
```

## URL's da aplicação
Ao rodar a aplicação, as seguintes URL's podem ser uteis:
  - Banco de Dados H2: http://localhost:8080/h2
  - Swagger UI OpenAPI: http://localhost:8080/swagger-ui.html
  - Especificação OpenAPI: http://localhost:8080/api-docs

## Licença 
Este projeto está sob a licença do MIT. Veja o arquivo [LICENSE](/LICENSE) para mais detalhes.

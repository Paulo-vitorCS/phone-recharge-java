# Phone Recharges API
No contexto das telecomunicações o papel dos dispositivos móveis ocupa lugar de destaque, milhares de usuários diariamente realizam recargas nos seus dispositivos, tal processo que deve ser rápido e simples. O intuito do projeto é criar uma solução para dispositivos móveis de recargas de celular, com o foco na API desta solução. A solução foi implementada na linguagem Java, utilizando RESTful na construção da API.

#### Funcionalidades
  - Cadastro de cliente
  - Cadastro de recarga
  - Realizar uma recarga
  - Listar recargas anteriores do cliente
  - 
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

## URL's da aplicação
Ao rodar a aplicação, as seguintes URL's podem ser uteis:
  - Banco de Dados H2: http://localhost:8080/h2
  - Swagger UI OpenAPI: http://localhost:8080/swagger-ui.html
  - Especificação OpenAPI: http://localhost:8080/api-docs

## Licença 
Este projeto está sob a licença do MIT. Veja o arquivo [LICENSE](/LICENSE) para mais detalhes.

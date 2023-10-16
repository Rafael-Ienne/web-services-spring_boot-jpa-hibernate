# spring-boot-project

Projeto web services com Spring Boot e JPA / Hibernate, inspirado no curso Java Completo do Dr. Nelio Alves. Ele é rodado na parte do servidor e dividido em camadas: resourse layer (controladores REST, que são uma interface da aplicação com o back end, de forma a receber as requisições e responde-las de acordo com o comportamento do sistema), service layer(implementa regras de negócio) e data access layer(faz operações com as entidades). Este projeto trabalha com algumas noções básicas com banco de dados e tem o objetivo de fazer um CRUD (Create, Retrieve, Update, Delete) completo, criar uma base de dados automaticamente, povoá-la com uma carga inicial e tratar exceções.

## Ferramentas utilizadas:

Spring Boot: framework para criar a aplicação;

Pache Tomcat: container web para executar a aplicação;

Maven: gerenciador de dependências;

H2: banco de dados em memória para teste;

Postman: recurso para testar as requisições.

 

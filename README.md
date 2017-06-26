# SPOTIPPOS

Olá a todos! Sou Edwi Feitoza e este é a resposta que proponho ao desafio de **Spotippos** da Viva Real.



## O Desafio

O desafio escolhido para **Spotippos** foi o de backend, onde devem ser disponibilizados endpoints para cadastrar imóveis, além de endpoints para realizar consultas de imóveis cadastrados tanto por ID quanto por coordenadas geográficas.

## Tecnologias, técnicas e frameworks usados

Para realizar este desafio os seguintes recursos e tarefas foram implementados e usados:

* Java 8;
* Spring Boot Versão 1.5.4;
* Spring Framework Versão 4.3.9;
* Embedded Jetty Server versão 9.4.5 (O Apache Tomcat Embedded foi trocado por Jetty);
* Embedded MongoDB Server (Flap Doodle Embedded Mongo), que usa MongoDB 3.2;
* Mongeez 1.3.0 (Gerenciador automatizado para lidar com o MongoDB para a carga inicial de documentos);
* OpenPojo Versão 0.8.6 para testes de entidades de documentos do MongoDB;
* Rest-Assured Versão 2.9.0 para testes automatizados de serviços REST;
* Swagger Versão 1.5 paraa documentação dos endpoints;
* Lombok Versão 1.16 para geração automatizada de código (getters, setters, toString, Hashcode, Equals, etc), evitando boilerplate;
* Junit para testes automatizados;
* Usado DDD para design e organização do projeto;
* Uso de Spring Data MongoDB para interação com o banco de dados embeddado;

## Rodando o projeto

O projeto em Spring foi concebido para ser o mais simples possível.
Para executa-lo, uma vez que o mesmo tenha sido clonado basta executar:

```
mvn spring-boot:run
```
O serviço ficará disponível na porta 8080 do servidor onde o mesmo estiver rodando, por exemplo:

`
http://localhost:8080
`

Para executar os testes automatizados, basta executar na linha de comando:

```
mvn test
```

## Mensagens de erro personalizadas

Foram verificadas e codificadas condições de exceções que podem ocorrer ao longo da execução do projeto.

Todas elas retornam - tanto em nível de body quanto em nível de respostas de códigos HTTP - mensagens personalizadas do motivo pelo qual o erro aconteceu e o que deve ser verificado para corrigir a falha.

Um exemplo é quando se tenta cadastrar uma propriedade em um ponto de geolocalização onde já existe um imóvel cadastrado. O serviço devolve o código HTTP 409 (Conflito) e devolve também o seguinte body:

```
{
    "rootCause": "Erro ao salvar novo imóvel em Spotippos",
    "errors": [
        {
            "message": "Já existe um imóvel nesta região de Spottipos. Por favor, escolha outra coordenda de X e Y"
        }
    ]
}
```

## Melhorias futuras

Para melhorar o código em futuro breve, pretendo usar recursos para deixa-lo mais próximo de um cenário real, a saber:

* Uso de estratégias e bibliotecas de cache (provavelmente Guava);
* Uso de recursos de autenticação e autorização (JWT e OAuth2, respectivamente);
* Containerizar o projeto (usando Docker);



Enjoy it! ;)
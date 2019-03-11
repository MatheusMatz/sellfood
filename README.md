# Sell Food

Aplicação web focada em vendas de lanches;

## Getting Started

Essa aplicação foi construida com Spring Boot e tendo seu ambiente virtualizado em Docker, fazendo uso do script docker-compose.yml, 
é possível subir a aplicação de maneira fácil e sem complicação, pois ao executar o comando docker-compose up na raiz do projeto,
será criado os container necessários para manter a aplicação, como o nginx e o servidor mysql. 

### Pré-requisitos

É necessário que tenha instalado o docker na máquina que será usada para a aplicação.

```
Give examples
```

### Execução 

executar o comando docker-compose up -d


## Running the tests


### Justificativa do design de código

#### Serve-side

  Foi criado APIs rest seguindo a convenção de ter controllers responsáveis por fazer a comunicação com o cliente 

e services que mantém a regra de negócio e faz a interface entre o controller e o banco de dados, essa abordagem foi 
escolhida por oferecer uma melhor organização e uma centralização da regra de negócio, tendo assim classes com responsabilidades 
bem definidas. 

Para a comunicação com o banco de dados, temos as entidades que são a abstrações das tabelas usadas pela aplicação 
para objetos, por meio dos mapeamentos feitos com annotations do JPA: 

* @Entity 
* @Table 
* @Column 
* @ManyToMany 

E para cada entidade mapeada, temos seus respectivos repositórios (repository), que fazem as execuções das queries, 
como consultas ou persistência de algum dado. 

Por se tratar de uma aplicação voltada a vendas, temos a manipulação de dinheiro, e com isso optei em usar uma dependência 
para trabalhar com moedas, e assim ter uma melhor precisão durante as operações com o montante - [Moneta](https://mvnrepository.com/artifact/org.javamoney/moneta) - essa dependencia além de permitir efetuar cálculos mais precisos, 
tem a opção te alternar em diferentes tipos de moeda. No entanto , não foi possível usa-la como atributo de uma das entidades, 
pois cada atributo representa uma tabela, e o tipo que o objeto MonetaryAmount é não é suportado pelo banco. 

Com isso a criação de um Utils (DinheiroUtil), que fizesse o meio essa conversão entre o MonetaryAmount e o BigDecimal, foi 
a melhor abordagem para que fosse possível a fácil manipulação do dado e a conversão de moeda caso necessário. 

Outra funcionalidade implementada foi a aplicação de desconto dependendo dos ingredientes do lanche, e a melhor forma encontrada sem fazer ifs ou ~~gambiarras~~ soluções alternativas, o **__design pattern__** __chain of responsability__ veio a calhar, pois com uma classe (__CalculadorDeDescontos__) e um método (__calcula__), temos a aplicação de diversos comportamentos dependendo das condições 
estabelecidas nos descontos representados pelas suas respectivas classes (__LightDesconto, MuitaCarneDesconto, MuitoQueijoDesconto__), 
sendo que cada uma implementa a interface __Desconto__, requisito necessário para a implementação do padrão. 

Como há a possibilidade dos preços dos ingredientes alterarem, é necessário atualizar todos os preços dos lanches que tem o respectivo ingrediente, e para resolver essa questão é a atualização do ingrediente deverá ser feita pela requisição put passando o nome do ingrediente a ser atualizado, feito isso os lanches também serão atualizados. 

A estrutura de pastas segue o padrão de um projeto maven, tendo a pasta src para o código e a resource contendo os recursos necessários 
para a aplicação. 

#### Client-side 

O front é mais simples, por deixar todo processamento de dados com a responsábilidade do back, o front só foca em fazer a manipulação das informações e oferecer um feedback para o usuário de suas interações. 

É usado bootstrap para a estilização e jquery para os comportamentos da tela.

### Ambiente

É feito uso do docker-compose, para a criação dos containers. um para o servidor mysql, outro e para o nginx, esse responsável pelo redirecionamento das requisições na porta 80 para o back , esse estando no terceiro container responsável por manter a aplicação.

O uso do docker-compose para a aplicação foi visando a facilidade que se tem ao virtualizar o ambiente já com as configurações necessárias para se ter tudo rodando.

### Testes

Foram criadas classes de testes para as principais funcionalidades (calculo dos valores dos lanches de cardápio, regra para cálculo de preço e promoções).

## Deployment


## Built With

* [Travis](https://travis-ci.org/) - CI 
* [Docker](https://www.docker.com/) - Virtualização 
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Gustavo Matheus Garcia dos Santos** 


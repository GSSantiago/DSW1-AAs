# API para Sistema de Compra e Venda de Veículos 

Sistema REST API desenvolvido como atividade 2 da disciplina Desenvolvimento de Software Web1. 
O sistema permite que consultas dos veiculos e propostas possam ser feitas, além da criação de lojas e clientes.

## Equipe

- Camila Oliveira de Souza – Crud Lojas e clientes
- Guilherme Santiago – Setup inicial do projeto e criação e visualização dos veículos
- Guilherme Wisniewski – Visualização das propostas

## Tecnologias utilizadas

- **Spring MVC**
- **Spring Security**
- **Spring Data JPA**
- **Maven**
- **MySQL**


## Tipos de Usuário

| Tipo        | Acesso                                                                 |
|-------------|------------------------------------------------------------------------|
| Admin       | CRUD de clientes e lojas                                               |
| Loja        | Cadastro/listagem de veículos, análise de propostas                    |
| Cliente     | Propostas de compra, listagem de suas propostas                        |

## Como rodar o projeto

1. Clone o repositório;
2. No PowerShell: para rodar com os dados corretos do banco de dados, atribua às variáveis MYSQL_USER e MYSQL_PASSWORD sua senha e usuário corretos:
- $env:MYSQL_USER="root"
- $env:MYSQL_PASSWORD="sua_senha"
4. Compile e execute com Maven:
    `mvn spring-boot:run`
5. Acesse pelo seu testador de API com a base: http://localhost:8080/api

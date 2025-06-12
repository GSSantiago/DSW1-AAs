# Sistema de Compra e Venda de Veículos

Sistema web desenvolvido como atividade da disciplina **Desenvolvimento de Software Web1**. 
O sistema permite que clientes façam propostas para veículos cadastrados por lojas, com autenticação, envio de e-mails, internacionalização e validações completas.

## Equipe

- Camila Oliveira de Souza – Autenticação, CRUDs e Validações
- Guilherme Santiago – Cadastro/Listagem de Veículos e Estilo
- Guilherme Wisniewski – Propostas, Status e E-mails

## Tecnologias utilizadas

- **Spring MVC**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **JavaScript & CSS**
- **Maven**
- **MySQL**

## Funcionalidades

- Login com três tipos de usuários: **administrador**, **cliente** e **loja**.
- CRUD de clientes e lojas (admin).
- Cadastro de veículos com até 10 fotos (loja).
- Página pública com listagem e filtro por modelo.
- Propostas de compra por clientes autenticados.
- Análise e resposta da loja com envio de e-mails automáticos.
- Internacionalização (Português e Inglês).
- Validações de formulário e tratamento de erros com mensagens amigáveis.

## Tipos de Usuário

| Tipo        | Acesso                                                                 |
|-------------|------------------------------------------------------------------------|
| Admin       | CRUD de clientes e lojas                                               |
| Loja        | Cadastro/listagem de veículos, análise de propostas                    |
| Cliente     | Propostas de compra, listagem de suas propostas                        |

## Internacionalização

Disponível em dois idiomas:
- Português (`pt_BR`)
- Inglês (`en_US`)

A troca de idioma pode ser feita via ícones na interface.

## Como rodar o projeto

1. Clone o repositório;
2. Compile e execute com Maven:
    `mvn spring-boot:run`
4. Acesse: http://localhost:8080

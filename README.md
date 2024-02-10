# API de Locação de Jogos de Tabuleiro
Bem-vindo à API de locação de jogos de tabuleiro! Esta API foi criada para facilitar o acesso a uma variedade de jogos de tabuleiro a preços acessíveis, permitindo que você e seus amigos desfrutem de momentos divertidos juntos sem a necessidade de grandes investimentos.

---

# Funcionalidades

## Jogos
### Listar jogos

Rota: GET /games <br>

Response: Retorna uma lista dos jogos disponíveis para locação.


### Inserir um jogo

Rota: POST /games <br>

Request: Adicione um novo jogo enviando os detalhes no corpo da requisição.

Response: Retorna o jogo recém-criado, incluindo um ID único.

---

## Clientes

### Buscar um cliente por id

Rota: GET /customers/:id

Response: Retorna os detalhes do cliente com o ID correspondente.

### Inserir um cliente

Rota: POST /customers

Request: Forneça o nome e CPF do cliente no corpo da requisição.

Response: Retorna o cliente recém-criado, incluindo um ID único.

---

## Aluguéis
### Listar aluguéis

Rota: GET /rentals

Response: Retorna uma lista de todos os aluguéis registrados, incluindo detalhes do cliente e do jogo.

### Inserir um aluguel

Rota: POST /rentals

Request: Forneça o ID do cliente e o ID do jogo, juntamente com a duração do aluguel em dias.

Response: Registra um novo aluguel e retorna os detalhes completos, incluindo informações sobre o cliente e o jogo.

### Finalizar aluguel

Rota: PUT /rentals/:id/return

Response: Finaliza o aluguel especificado, registrando a data de retorno e quaisquer taxas adicionais aplicáveis.

---

## Testes
Foram feitos testes de integração e unidade para garantia de qualidade e identificação de problemas.

---

## Link de deploy

[Link da API](https://boardcamp-api-vdvj.onrender.com)

---

Esperamos que esta API torne suas experiências com jogos de tabuleiro ainda mais divertidas! Se precisar de assistência ou tiver alguma dúvida, não hesite em entrar em contato. Aproveite! 🎲🎉

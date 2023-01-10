# Tic-Tac-Toe 
To implement the Tic Tac Toe game, follow these steps:

1. Clone the repository to your local machine.

2. Create a MySQL database with the name tictactoe and update the spring.datasource.url field in the application.properties file.

3. In the application.properties file, update the spring.datasource.username and spring.datasource.password with your MySQL username and password.

4. Set the server port as 8081

Endpoints:

* ``` /auth/register ```: This endpoint is used to register a new user.

* ``` /auth/login ```: This endpoint is used to login existing user. It requires the following fields in the request body: email and password.

* ``` /games ```: This endpoint is used to get all the games for a user.

* ``` /games/create-game ```: This endpoint is used to create a new game. It requires the following fields in the request body: player2Id, player1Id.

* ``` /games/{gameId}/move ```: This endpoint is used to make a move for a specific game. It requires the following fields in the request body: playerId, row, col

* ``` /games/user/{userId} ```: This endpoint is used to get the details of a specific game using userID.

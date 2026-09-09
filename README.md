# unbeatableTicTacToe

A console-based Tic-Tac-Toe game in Java where you play against an AI opponent that never loses. The AI uses the minimax algorithm to evaluate every possible outcome and always picks the optimal move, so the best a human player can achieve is a draw.

## How it works

- The board is a 3x3 grid represented as a 9-element array, with each empty slot labeled 1-9 to make command-line input simple.
- `Player` reads the human's move from the console and validates that it's a number between 1 and 9.
- `AIPlayer` extends `Player` and implements the minimax algorithm: it recursively simulates every possible continuation of the game, scoring a win for the AI as `+10`, a win for the human as `-10`, and a draw as `0`, then chooses the move with the best guaranteed outcome.
- `Board` tracks game state, renders the board to the console, and checks all rows, columns, and diagonals for a winner or draw.
- `TicTacToe` contains the `main` method and drives the turn-by-turn game loop, alternating between the human (`X`) and the AI (`O`).

## Requirements

- Java 8 (JDK)
- Maven

## Building

```bash
mvn compile
```

## Running

```bash
mvn compile exec:java -Dexec.mainClass="ticTacToe.ticTacToe.TicTacToe"
```

Or, after compiling, run directly with `java` using the `target/classes` output directory on the classpath:

```bash
java -cp target/classes ticTacToe.ticTacToe.TicTacToe
```

Once running, follow the on-screen prompts and enter a number (1-9) corresponding to an open space on the board to make your move. `X` always goes first.

## Testing

The project is set up with JUnit, Hamcrest, and Mockito as test dependencies. Run the test suite with:

```bash
mvn test
```

## Project structure

```
src/main/java/ticTacToe/ticTacToe/
├── TicTacToe.java   # Entry point and game loop
├── Board.java       # Board state, rendering, and win/draw detection
├── Player.java      # Human player input and identity
└── AIPlayer.java    # Minimax-based AI opponent
```

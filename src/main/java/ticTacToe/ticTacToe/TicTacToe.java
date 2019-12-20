package ticTacToe.ticTacToe;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Board board = new Board();
		Player player = new Player("Player 1");
		AIPlayer aiPlayer = new AIPlayer("AI");
		String winner = null;
		String turn = "X";

		// board.createTestBoard();
		board.populateEmptyBoard();
		System.out.println("Welcome to 2-player tic-tac-toe");
		board.printBoard();
		System.out.println("X's will go first. Please enter the number of an open space:");

		while (winner == null) {
			if (turn.equals("X")) {
				int playerChoice = player.getPlayerChoice();
				board.addToBoard(playerChoice, player.getMarker());
				// board.printBoard();
				winner = board.checkWinner();
				turn = "O";
				// System.out.println(turn + "'s turn. Choose a space to place an " + turn);
			} else {
				aiPlayer.callMiniMax(board, 0, aiPlayer);
				board.addToBoard(aiPlayer.returnBestMove(aiPlayer.getScoresMap()), aiPlayer.getMarker());
				board.printBoard();
				winner = board.checkWinner();
				turn = "X";
				if (winner == null) {
					System.out.println(turn + "'s turn. Choose a space to place an " + turn);
				}
			}
		}

		board.printBoard();
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("The game is a draw");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won the game!");
		}

	}

}

package ticTacToe.ticTacToe;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Board board = new Board();
		Player player = new Player("Player 1");
		AIPlayer aiPlayer = new AIPlayer("AI");

		board.populateEmptyBoard();
		System.out.println("Welcome to 2-player tic-tac-toe");
		board.printBoard();
		System.out.println("X's will go first. Please enter the number of an open space:");

		String winner = playGame(board, player, aiPlayer);

		board.printBoard();
		announceResult(winner);

	}

	static String playGame(Board board, Player player, AIPlayer aiPlayer) {
		String winner = null;
		String turn = "X";
		while(winner == null) {
			if (turn.equals("X")) {
				playHumanTurn(board, player);
				winner = board.checkWinner();
				turn = "O";
			} else {
				playAiTurn(board, aiPlayer);
				winner = board.checkWinner();
				turn = "X";
				if (winner == null) {
					System.out.println(turn + "'s turn. Choose a space to place an " + turn);
				}
			}
		}
		return winner;
	}

	static void playHumanTurn(Board board, Player player) {
		boolean wasValidMove = false;
		while (!wasValidMove) {
			int playerChoice = player.getPlayerChoice();
			wasValidMove = board.addToBoard(playerChoice, player.getMarker());
		}
		board.printBoard();
	}

	static void playAiTurn(Board board, AIPlayer aiPlayer) {
		Board clone = board.cloneBoard(board);
		aiPlayer.callMiniMax(clone, 0);
		board.addToBoard(aiPlayer.returnBestMove(aiPlayer.getScoresMap()), aiPlayer.getMarker());
		board.printBoard();
	}

	static void announceResult(String winner) {
		if (winner.equalsIgnoreCase("draw")) {
			System.out.println("The game is a draw");
		} else {
			System.out.println("Congratulations! " + winner + "'s have won the game!");
		}
	}

}

package ticTacToe.ticTacToe;

import java.util.Arrays;

public class Board {
	
	private String[] board = new String[9];

	private static final int[][] WIN_LINES = {
			{0,1,2}, {3,4,5}, {6,7,8},
			{0,3,6}, {1,4,7}, {2,5,8},
			{0,4,8}, {2,4,6}
	};
	
	public Board() {
		
	}
	
	
	public String[] getBoard() {
		return board;
	}
	


	public void setBoard(String[] board) {
		this.board = board;
	}



	public boolean addToBoard(int playerChoice, String marker) {
		boolean isValidMove = false;
		if(this.board[playerChoice - 1].equals(String.valueOf(playerChoice))) {
			this.board[playerChoice - 1] = marker;
			isValidMove = true;
		}
		else {
			System.out.println("Slot already taken. Please choose again.");
		}
		return isValidMove;
	}


	
	public void printBoard() {
		System.out.println("-------------");
		System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
		System.out.println("-------------");
		System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
		System.out.println("-------------");
		System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
		System.out.println("-------------");
	}
	
	public void populateEmptyBoard() {
		for(int i = 0; i < board.length ; i ++) {
			board[i] = String.valueOf(i + 1);	
			}
	}
	
	public String checkWinner() {
		for(int[] line: WIN_LINES) {
			String cells = board[line[0]] + board[line[1]] + board[line[2]];
			if (cells.equals("XXX")) return "X";
			if (cells.equals("OOO")) return "O";
		}
		for(int i = 0; i < 9; i ++) {
			if(Arrays.asList(board).contains(String.valueOf(i + 1))) {
				break;
			} 
			else if(i == 8) {
				return "DRAW";
			}
		}
		return null;
	}
	
	public Board cloneBoard(Board board) {
		Board clonedBoard = new Board();
		clonedBoard.setBoard(Arrays.copyOf(board.getBoard(), board.getBoard().length));
		return clonedBoard;
	}
	
	public void createTestBoard() {
		board[0] = "1";
		board[1] = "2";
		board[2] = "3";
		board[3] = "X";
		board[4] = "O";
		board[5] = "6";
		board[6] = "O";
		board[7] = "X";
		board[8] = "O";
		
	}
	
	

}

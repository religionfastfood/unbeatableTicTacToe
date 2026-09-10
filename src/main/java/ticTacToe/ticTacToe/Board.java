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
		if(this.board[playerChoice - 1] == null) {
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
		System.out.println("| " + display(0) + " | " + display(1) + " | " + display(2) + " |");
		System.out.println("-------------");
		System.out.println("| " + display(3) + " | " + display(4) + " | " + display(5) + " |");
		System.out.println("-------------");
		System.out.println("| " + display(6) + " | " + display(7) + " | " + display(8) + " |");
		System.out.println("-------------");
	}

	private String display(int i) {
		return board[i] == null ? String.valueOf(i + 1) : board[i];
	}
	
	public void populateEmptyBoard() {
        Arrays.fill(board, null);
	}
	
	public String checkWinner() {
		for(int[] line: WIN_LINES) {
			String cells = board[line[0]] + board[line[1]] + board[line[2]];
			if (cells.equals("XXX")) return "X";
			if (cells.equals("OOO")) return "O";
		}
		boolean hasEmpty = Arrays.asList(board).contains(null);
		return hasEmpty ? null : "DRAW";
	}
	
	public Board cloneBoard(Board board) {
		Board clonedBoard = new Board();
		clonedBoard.setBoard(Arrays.copyOf(board.getBoard(), board.getBoard().length));
		return clonedBoard;
	}

}

package ticTacToe.ticTacToe;

import java.util.Arrays;

public class Board {
	
	private String[] board = new String[9];
	
	
	public Board() {
		
	}
	
	
	public String[] getBoard() {
		return board;
	}
	


	public void setBoard(String[] board) {
		this.board = board;
	}



	public void addToBoard(int playerChoice, String marker) {
		if(this.board[playerChoice - 1].equals(String.valueOf(playerChoice))) {
			this.board[playerChoice - 1] = marker;
		}
		else {
			System.out.println("Slot already taken. Please choose again.");
		}
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
		for(int i = 0; i < 8; i ++) {
			String line = null;
			switch (i) {
			case 0: 
				line = board[0] + board[1] + board [2];
				break;
			case 1: 
				line = board[3] + board[4] + board [5];
				break;
			case 2: 
				line = board[6] + board[7] + board [8];
				break;
			case 3: 
				line = board[0] + board[3] + board [6];
				break;
			case 4: 
				line = board[1] + board[4] + board [7];
				break;
			case 5: 
				line = board[2] + board[5] + board [8];
				break;
			case 6: 
				line = board[0] + board[4] + board [8];
				break;
			case 7: 
				line = board[2] + board[4] + board [6];
				break;
		
			}
			if(line.equals("XXX")) {
				return "X";
			}
			if(line.equals("OOO")){
				return "O";
			}
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
		clonedBoard.setBoard(board.getBoard());
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

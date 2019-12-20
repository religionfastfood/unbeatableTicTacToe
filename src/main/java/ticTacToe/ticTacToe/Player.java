package ticTacToe.ticTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
	
	private String name;
	private String marker = "X";
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
		
	}
	
	public String getName(){
		return name;
		
	}
	
	public String getMarker() {
		return marker;
	}
	
	public void setMarker(String marker){
		this.setMarker(marker);
	}
	
	public int getPlayerChoice() {
		Scanner scanner = new Scanner(System.in);
		
		int playerChoice = scanner.nextInt();
		
		while(!(playerChoice > 0 && playerChoice <= 9 )) {
			System.out.println(playerChoice + " is not a valid input. Please enter a number between 1 and 9, inclusive: ");
			playerChoice = scanner.nextInt();	
		} 
		
		return playerChoice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marker == null) ? 0 : marker.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (marker == null) {
			if (other.marker != null)
				return false;
		} else if (!marker.equals(other.marker))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	

}

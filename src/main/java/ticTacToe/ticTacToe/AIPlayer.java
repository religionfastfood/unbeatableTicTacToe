package ticTacToe.ticTacToe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AIPlayer extends Player {

	private String name;
	private String marker = "O";
	private Map<Integer, Integer> rootPositionScoreMap;

	public String getName() {
		return name;
	}

	public AIPlayer() {

	}

	public AIPlayer(String name) {
		this.name = name;

	}

	public String getMarker() {
		return marker;
	}

	public Map<Integer, Integer> getScoresMap() {
		return rootPositionScoreMap;
	}

	public List<Integer> getEmptySpots(Board board) {
		List<Integer> emptySpots = new ArrayList<Integer>();
		for (int i = 0; i < board.getBoard().length; i++) {
			if (board.getBoard()[i].equals("X") || board.getBoard()[i].equals("O")) {
				continue;
			} else {
				emptySpots.add(i + 1);
			}
		}
		return emptySpots;
	}

	public int returnMax(List<Integer> aList) {
		int max = -10000;
		for (int i : aList) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public int returnMin(List<Integer> aList) {
		int min = 10000;
		for (int i : aList) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	public int returnBestMove(Map<Integer, Integer> aMap) {
		int bestMoveScore = 0;
		int bestMove = 0;
		for (Map.Entry<Integer, Integer> entry : aMap.entrySet()) {
			if (entry.getValue() > bestMoveScore) {
				bestMoveScore = entry.getValue();
				bestMove = entry.getKey();
			} else if (entry.getValue() <= 0 && bestMove == 0) {
				bestMoveScore = entry.getValue();
				bestMove = entry.getKey();
			}
		}
		return bestMove;
	}

	public void callMiniMax(Board board, int depth, Player player) {
		rootPositionScoreMap = new LinkedHashMap<Integer, Integer>();
		miniMax(board, depth, player);
	}

	public int miniMax(Board board, int depth, Player player) {
		Player huPlayer = new Player("Human");
		Player aiPlayer = new AIPlayer("AI");
		List<Integer> emptySpots = getEmptySpots(board);

		if (board.checkWinner() != null) {

			if (board.checkWinner().equals("X")) {
				return -10;
			} else if (board.checkWinner().equals("O")) {
				return 10;
			} else if (board.checkWinner().equals("DRAW")) {
				return 0;
			}
		}

		List<Integer> scores = new ArrayList<Integer>(); 

		for (int i = 0; i < emptySpots.size(); ++i) {

			if (player.getName().equals("AI")) {
				board.addToBoard(emptySpots.get(i), aiPlayer.getMarker());
				int currentScore = miniMax(board, depth + 1, huPlayer);
				scores.add(currentScore);

				if (depth == 0) {
					rootPositionScoreMap.put(emptySpots.get(i), currentScore);
				}
			} else if (player.getName().equals("Human")) {
				board.addToBoard(emptySpots.get(i), huPlayer.getMarker());
				int currentScore = miniMax(board, depth + 1, aiPlayer);
				scores.add(currentScore);
			}

			board.getBoard()[emptySpots.get(i) - 1] = String.valueOf(emptySpots.get(i));
		}
		return player.getName().equals("AI") ? returnMax(scores) : returnMin(scores);

	}

}

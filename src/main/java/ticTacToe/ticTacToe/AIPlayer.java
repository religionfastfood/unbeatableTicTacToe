package ticTacToe.ticTacToe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AIPlayer extends Player {

	private Map<Integer, Integer> rootPositionScoreMap;

	public AIPlayer() {
		super(null, "O");
	}

	public AIPlayer(String name) {
		super(name, "O");

	}

	public Map<Integer, Integer> getScoresMap() {
		return rootPositionScoreMap;
	}

	public List<Integer> getEmptySpots(Board board) {
		List<Integer> emptySpots = new ArrayList<>();
		for (int i = 0; i < board.getBoard().length; i++) {
			if (board.getBoard()[i] == null) {
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

	public void callMiniMax(Board board, int depth) {
		rootPositionScoreMap = new LinkedHashMap<Integer, Integer>();
		miniMax(board, depth, true);
	}

	public int miniMax(Board board, int depth, boolean isAiTurn) {
		List<Integer> emptySpots = getEmptySpots(board);

		String winner = board.checkWinner();
		if (winner != null) {

			if (winner.equals("X")) {
				return -10;
			} else if (winner.equals("O")) {
				return 10;
			} else if (winner.equals("DRAW")) {
				return 0;
			}
		}

		List<Integer> scores = new ArrayList<Integer>(); 

		for (int spot: emptySpots) {
			board.addToBoard(spot, isAiTurn ? getMarker() : "X");
			int currentScore = miniMax(board, depth + 1, !isAiTurn);
			scores.add(currentScore);

			if (isAiTurn && depth == 0) {
				rootPositionScoreMap.put(spot, currentScore);
			}
			board.getBoard()[spot - 1] = null;
		}
		return isAiTurn ? returnMax(scores) : returnMin(scores);

	}

}

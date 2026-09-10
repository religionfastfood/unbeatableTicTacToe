package ticTacToe.ticTacToe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AIPlayerTest {

	private AIPlayer aiPlayer;
	private Board board;

	@Before
	public void setUp() {
		aiPlayer = new AIPlayer("AI");
		board = new Board();
		board.populateEmptyBoard();
	}

	@Test
	public void getEmptySpots_returnsOnlyThePositionsThatAreStillOpen() {
		board.addToBoard(1, "X");
		board.addToBoard(5, "O");

		List<Integer> emptySpots = aiPlayer.getEmptySpots(board);

		assertEquals(Arrays.asList(2, 3, 4, 6, 7, 8, 9), emptySpots);
	}

	@Test
	public void returnMax_returnsTheLargestValueInTheList() {
		assertEquals(8, aiPlayer.returnMax(Arrays.asList(-10, 0, 8, 3)));
	}

	@Test
	public void returnMin_returnsTheSmallestValueInTheList() {
		assertEquals(-10, aiPlayer.returnMin(Arrays.asList(-10, 0, 8, 3)));
	}

	@Test
	public void returnBestMove_picksTheMoveWithTheHighestScore() {
		Map<Integer, Integer> scores = new LinkedHashMap<Integer, Integer>();
		scores.put(3, 0);
		scores.put(5, 10);
		scores.put(7, -10);

		assertEquals(5, aiPlayer.returnBestMove(scores));
	}

	@Test
	public void returnBestMove_picksTheLeastBadMoveWhenEveryScoreIsNegative() {
		Map<Integer, Integer> scores = new LinkedHashMap<Integer, Integer>();
		scores.put(3, -5);
		scores.put(5, -3);
		scores.put(7, -8);

		assertEquals(5, aiPlayer.returnBestMove(scores));
	}

	@Test
	public void miniMax_choosesTheMoveThatWinsImmediately() {
		// O | O | _   -> AI (O) can win now by playing position 3
		// X | X | _
		// _ | _ | _
		String[] boardState = { "O", "O", null, "X", "X", null, null, null, null };
		board.setBoard(boardState);

		aiPlayer.callMiniMax(board, 0);
		int bestMove = aiPlayer.returnBestMove(aiPlayer.getScoresMap());

		assertEquals(3, bestMove);
		assertEquals(Integer.valueOf(10), aiPlayer.getScoresMap().get(3));
	}

	@Test
	public void miniMax_blocksTheOpponentsWinningMove() {
		// X | X | _   -> human (X) threatens to win at position 3, AI (O) must block
		// O | _ | _
		// _ | _ | _
		String[] boardState = { "X", "X", null, "O", null, null, null, null, null };
		board.setBoard(boardState);

		aiPlayer.callMiniMax(board, 0);
		int bestMove = aiPlayer.returnBestMove(aiPlayer.getScoresMap());

		assertEquals(3, bestMove);
	}

	@Test
	public void miniMax_neverAllowsTheAiToLoseAFullGameAgainstOptimalPlay() {
		// Simulates a full game where the human always plays optimally
		// (taking the center, then a corner) and the AI relies purely on
		// minimax. The AI must draw or win -- never lose.
		board.addToBoard(1, "X"); // human takes a corner
		playAiMove();
		board.addToBoard(9, "X"); // human takes the opposite corner
		playAiMove();
		board.addToBoard(3, "X");
		playAiMove();
		board.addToBoard(7, "X");
		playAiMove();

		String winner = board.checkWinner();
		assertFalse("AI should never lose", "X".equals(winner));
	}

	private void playAiMove() {
		if (board.checkWinner() != null) {
			return;
		}
		aiPlayer.callMiniMax(board, 0);
		int bestMove = aiPlayer.returnBestMove(aiPlayer.getScoresMap());
		board.addToBoard(bestMove, aiPlayer.getMarker());
	}
}

package ticTacToe.ticTacToe;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() {
		board = new Board();
		board.populateEmptyBoard();
	}

	@Test
	public void populateEmptyBoard_fillsEachSlotWithNull() {
		String[] expected = { null, null, null, null, null, null, null, null, null };
		assertArrayEquals(expected, board.getBoard());
	}

	@Test
	public void addToBoard_placesMarkerInEmptySlot() {
		board.addToBoard(5, "X");
		assertEquals("X", board.getBoard()[4]);
	}

	@Test
	public void addToBoard_doesNotOverwriteAnAlreadyTakenSlot() {
		board.addToBoard(5, "X");
		board.addToBoard(5, "O");
		assertEquals("X", board.getBoard()[4]);
	}

	@Test
	public void checkWinner_returnsNullWhenGameIsStillInProgress() {
		board.addToBoard(1, "X");
		board.addToBoard(5, "O");
		assertNull(board.checkWinner());
	}

	@Test
	public void checkWinner_detectsARowWin() {
		board.addToBoard(1, "X");
		board.addToBoard(2, "X");
		board.addToBoard(3, "X");
		assertEquals("X", board.checkWinner());
	}

	@Test
	public void checkWinner_detectsAColumnWin() {
		board.addToBoard(2, "O");
		board.addToBoard(5, "O");
		board.addToBoard(8, "O");
		assertEquals("O", board.checkWinner());
	}

	@Test
	public void checkWinner_detectsADiagonalWin() {
		board.addToBoard(1, "X");
		board.addToBoard(5, "X");
		board.addToBoard(9, "X");
		assertEquals("X", board.checkWinner());
	}

	@Test
	public void checkWinner_detectsTheOtherDiagonalWin() {
		board.addToBoard(3, "O");
		board.addToBoard(5, "O");
		board.addToBoard(7, "O");
		assertEquals("O", board.checkWinner());
	}

	@Test
	public void checkWinner_detectsADraw() {
		// X | O | X
		// X | O | O
		// O | X | X
		String[] fullBoardNoWinner = { "X", "O", "X", "X", "O", "O", "O", "X", "X" };
		board.setBoard(fullBoardNoWinner);
		assertEquals("DRAW", board.checkWinner());
	}

	@Test
	public void cloneBoard_copiesCurrentBoardState() {
		board.addToBoard(1, "X");
		board.addToBoard(5, "O");

		Board cloned = board.cloneBoard(board);

		assertArrayEquals(board.getBoard(), cloned.getBoard());
	}

	private Board buildFixtureBoard() {
		Board b = new Board();
		b.setBoard(new String[]{null, null, null, "X", "O", null, "O", "X", "O"});
		return b;
	}
}

package ticTacToe.ticTacToe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

public class PlayerTest {

	private final InputStream originalSystemIn = System.in;

	@After
	public void restoreSystemIn() {
		System.setIn(originalSystemIn);
	}

	@Test
	public void constructorSetsTheGivenName() {
		Player player = new Player("Player 1");
		assertEquals("Player 1", player.getName());
	}

	@Test
	public void defaultMarkerIsX() {
		Player player = new Player("Player 1");
		assertEquals("X", player.getMarker());
	}

	@Test
	public void getPlayerChoice_returnsAValidNumberImmediately() {
		System.setIn(new ByteArrayInputStream("5\n".getBytes()));
		Player player = new Player("Player 1");

		assertEquals(5, player.getPlayerChoice());
	}

	@Test
	public void getPlayerChoice_repromptsUntilAValidNumberIsEntered() {
		System.setIn(new ByteArrayInputStream("0\n10\n7\n".getBytes()));
		Player player = new Player("Player 1");

		assertEquals(7, player.getPlayerChoice());
	}

	@Test
	public void equals_isTrueForSameNameAndMarker() {
		Player playerOne = new Player("Player 1");
		Player playerTwo = new Player("Player 1");

		assertTrue(playerOne.equals(playerTwo));
		assertEquals(playerOne.hashCode(), playerTwo.hashCode());
	}

	@Test
	public void equals_isFalseForDifferentNames() {
		Player playerOne = new Player("Player 1");
		Player playerTwo = new Player("Player 2");

		assertFalse(playerOne.equals(playerTwo));
	}

	@Test
	public void equals_isFalseWhenComparedToNull() {
		Player player = new Player("Player 1");
		assertFalse(player.equals(null));
	}

	@Test
	public void equals_isFalseWhenComparedToADifferentClass() {
		Player player = new Player("Player 1");
		assertFalse(player.equals("Player 1"));
	}

	@Test
	public void equals_isTrueForSameInstance() {
		Player player = new Player("Player 1");
		assertTrue(player.equals(player));
	}
}

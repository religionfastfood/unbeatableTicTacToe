package ticTacToe.ticTacToe;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicTacToeTest {

    private final InputStream originalIn = System.in;

    @After
    public void restoreSystemIn() {
        System.setIn(originalIn);
    }

    @Test
    public void playHumanTurn_placesMoveAndRedrawsBoard() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        Board board = new Board();
        board.populateEmptyBoard();
        Player player = new Player("Test");

        TicTacToe.playHumanTurn(board,player);

        assertEquals("X", board.getBoard()[4]);
    }

    @Test
    public void playHumanTurn_reprompts_untilAValidMoveIsEntered() {
        System.setIn(new ByteArrayInputStream("5\n3\n".getBytes()));
        Board board = new Board();
        board.populateEmptyBoard();
        board.addToBoard(5, "O"); // pre-occupy the first choice

        TicTacToe.playHumanTurn(board, new Player("Test"));

        assertEquals("X", board.getBoard()[2]); //fell through to the second choice
    }

    @Test
    public void playAiTurn_placesOneAiMoveOnTheBoard() {
        Board board = new Board();
        board.populateEmptyBoard();
        board.addToBoard(1, "X");

        TicTacToe.playAiTurn(board, new AIPlayer("AI"));

        long oCount = java.util.Arrays.stream(board.getBoard()).filter("O"::equals).count();
        assertEquals(1, oCount);
    }

    @Test
    public void playGame_terminatesWithAWinnerOrADraw() {
        System.setIn(new ByteArrayInputStream("1\n2\n3\n4\n5\n6\n7\n8\n9\n".getBytes()));
        Board board = new Board();
        board.populateEmptyBoard();

        String winner = TicTacToe.playGame(board, new Player("Test"), new AIPlayer("AI"));

        assertNotNull(winner);
    }
}

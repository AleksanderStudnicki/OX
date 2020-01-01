package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class BoardCheckerTest {

  public void shouldReturnFalseOnBoard() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.setField(0, Sign.NAUGHT);
    board.setField(1, Sign.CROSS);
    board.setField(2, Sign.NAUGHT);

    //then
    assertFalse(boardChecker.isWinner(2, board));
  }

  public void shouldReturnTrueOnBoardNaughtInFirstRow() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.setField(0, Sign.NAUGHT);
    board.setField(1, Sign.NAUGHT);
    board.setField(2, Sign.NAUGHT);

    //then
    assertTrue(boardChecker.isWinner(2, board));
  }

  public void shouldReturnTrueOnBoardCrossInFirstColumn() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.setField(0, Sign.CROSS);
    board.setField(3, Sign.CROSS);
    board.setField(5, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(3, board));
  }

  public void shouldReturnTrueOnBoardDiagonalFromFirstField() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.setField(0, Sign.CROSS);
    board.setField(4, Sign.CROSS);
    board.setField(8, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(0, board));
  }

  public void shouldReturnTrueOnBoardDiagonalFromThirdField() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.setField(3, Sign.CROSS);
    board.setField(4, Sign.CROSS);
    board.setField(6, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(4, board));
  }
}

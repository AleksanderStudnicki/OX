package app.studnicki.ox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class BoardCheckerTest {

  public void shouldReturnFalseOnBoard() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.CROSS);
    board.markASign(2, Sign.NAUGHT);

    //then
    assertFalse(boardChecker.isWinner(2, board));
    assertFalse(boardChecker.isWinner(1, board));
    assertFalse(boardChecker.isWinner(2, board));
  }

  public void shouldReturnTrueOnBoardNaughtInFirstRow() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.NAUGHT);
    board.markASign(2, Sign.NAUGHT);

    //then
    assertTrue(boardChecker.isWinner(0, board));
    assertTrue(boardChecker.isWinner(1, board));
    assertTrue(boardChecker.isWinner(2, board));
  }

  public void shouldReturnTrueOnBoardCrossInFirstColumn() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(0, board));
    assertTrue(boardChecker.isWinner(3, board));
    assertTrue(boardChecker.isWinner(6, board));
  }

  public void shouldReturnTrueOnBoardDiagonalFromFirstField() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(8, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(0, board));
    assertTrue(boardChecker.isWinner(4, board));
    assertTrue(boardChecker.isWinner(8, board));
  }

  public void shouldReturnTrueOnBoardDiagonalFromThirdField() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(2, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(2, board));
    assertTrue(boardChecker.isWinner(4, board));
    assertTrue(boardChecker.isWinner(6, board));
  }

  public void shouldReturnFalseOnThatDiagonal() {
    //given
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(1, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(5, Sign.CROSS);
    board.markASign(7, Sign.CROSS);

    //then
    assertFalse(boardChecker.isWinner(1, board));
    assertFalse(boardChecker.isWinner(3, board));
    assertFalse(boardChecker.isWinner(5, board));
    assertFalse(boardChecker.isWinner(7, board));
  }

  public void shouldReturnTrueOnThatDiagonal5() {
    //given
    BoardChecker boardChecker = new BoardChecker(4);
    Board board = new Board(5);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(6, Sign.CROSS);
    board.markASign(12, Sign.CROSS);
    board.markASign(18, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(0, board));
    assertTrue(boardChecker.isWinner(6, board));
    assertTrue(boardChecker.isWinner(12, board));
    assertTrue(boardChecker.isWinner(18, board));
  }
}
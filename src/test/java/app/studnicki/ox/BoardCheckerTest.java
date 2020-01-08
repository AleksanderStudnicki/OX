package app.studnicki.ox;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

@Test
public class BoardCheckerTest {

  public void shouldReturnFalseOnBoard() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.CROSS);
    board.markASign(2, Sign.NAUGHT);

    //then
    soft.assertFalse(boardChecker.isWinner(0));
    soft.assertFalse(boardChecker.isWinner(1));
    soft.assertFalse(boardChecker.isWinner(2));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardNaughtInFirstRow() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.NAUGHT);
    board.markASign(2, Sign.NAUGHT);

    //then
    soft.assertTrue(boardChecker.isWinner(0));
    soft.assertTrue(boardChecker.isWinner(1));
    soft.assertTrue(boardChecker.isWinner(2));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardCrossInFirstColumn() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0));
    soft.assertTrue(boardChecker.isWinner(3));
    soft.assertTrue(boardChecker.isWinner(6));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardDiagonalFromFirstField() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(8, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0));
    soft.assertTrue(boardChecker.isWinner(4));
    soft.assertTrue(boardChecker.isWinner(8));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardDiagonalFromThirdField() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(2, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(2));
    soft.assertTrue(boardChecker.isWinner(4));
    soft.assertTrue(boardChecker.isWinner(6));

    soft.assertAll();
  }

  public void shouldReturnFalseOnThatDiagonal() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(3);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(1, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(5, Sign.CROSS);
    board.markASign(7, Sign.CROSS);

    //then
    soft.assertFalse(boardChecker.isWinner(1));
    soft.assertFalse(boardChecker.isWinner(3));
    soft.assertFalse(boardChecker.isWinner(5));
    soft.assertFalse(boardChecker.isWinner(7));

    soft.assertAll();
  }

  public void shouldReturnTrueOnThatDiagonal5() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(5);
    BoardChecker boardChecker = new BoardChecker(4, board);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(6, Sign.CROSS);
    board.markASign(12, Sign.CROSS);
    board.markASign(18, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0));
    soft.assertTrue(boardChecker.isWinner(6));
    soft.assertTrue(boardChecker.isWinner(12));
    soft.assertTrue(boardChecker.isWinner(18));

    soft.assertAll();
  }

  public void autoPlayBugFixTest() {
    //given
    SoftAssert soft = new SoftAssert();
    Board board = new Board(4);
    BoardChecker boardChecker = new BoardChecker(3, board);

    //when
    board.markASign(13, Sign.CROSS);
    board.markASign(1, Sign.NAUGHT);
    board.markASign(14, Sign.CROSS);
    board.markASign(8, Sign.NAUGHT);
    board.markASign(15, Sign.CROSS);

    //then
    assertTrue(boardChecker.isWinner(15));
  }

}

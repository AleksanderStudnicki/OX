package app.studnicki.ox;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class BoardCheckerTest {

  public void shouldReturnFalseOnBoard() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.CROSS);
    board.markASign(2, Sign.NAUGHT);

    //then
    soft.assertFalse(boardChecker.isWinner(0, board));
    soft.assertFalse(boardChecker.isWinner(1, board));
    soft.assertFalse(boardChecker.isWinner(2, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardNaughtInFirstRow() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.NAUGHT);
    board.markASign(2, Sign.NAUGHT);

    //then
    soft.assertTrue(boardChecker.isWinner(0, board));
    soft.assertTrue(boardChecker.isWinner(1, board));
    soft.assertTrue(boardChecker.isWinner(2, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardCrossInFirstColumn() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0, board));
    soft.assertTrue(boardChecker.isWinner(3, board));
    soft.assertTrue(boardChecker.isWinner(6, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardDiagonalFromFirstField() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(8, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0, board));
    soft.assertTrue(boardChecker.isWinner(4, board));
    soft.assertTrue(boardChecker.isWinner(8, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnBoardDiagonalFromThirdField() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(2, Sign.CROSS);
    board.markASign(4, Sign.CROSS);
    board.markASign(6, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(2, board));
    soft.assertTrue(boardChecker.isWinner(4, board));
    soft.assertTrue(boardChecker.isWinner(6, board));

    soft.assertAll();
  }

  public void shouldReturnFalseOnThatDiagonal() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(3);

    //when
    board.markASign(1, Sign.CROSS);
    board.markASign(3, Sign.CROSS);
    board.markASign(5, Sign.CROSS);
    board.markASign(7, Sign.CROSS);

    //then
    soft.assertFalse(boardChecker.isWinner(1, board));
    soft.assertFalse(boardChecker.isWinner(3, board));
    soft.assertFalse(boardChecker.isWinner(5, board));
    soft.assertFalse(boardChecker.isWinner(7, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnThatDiagonal5() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(4);
    Board board = new Board(5);

    //when
    board.markASign(0, Sign.CROSS);
    board.markASign(6, Sign.CROSS);
    board.markASign(12, Sign.CROSS);
    board.markASign(18, Sign.CROSS);

    //then
    soft.assertTrue(boardChecker.isWinner(0, board));
    soft.assertTrue(boardChecker.isWinner(6, board));
    soft.assertTrue(boardChecker.isWinner(12, board));
    soft.assertTrue(boardChecker.isWinner(18, board));

    soft.assertAll();
  }

  public void shouldReturnTrueOnThatCrossesInTheLastLineFromSecondWhenWinningRuleIsLesserThanDimension() {
    //given
    SoftAssert soft = new SoftAssert();
    BoardChecker boardChecker = new BoardChecker(3);
    Board board = new Board(4);

    //when
    board.markASign(13, Sign.CROSS);
    board.markASign(1, Sign.NAUGHT);
    board.markASign(14, Sign.CROSS);
    board.markASign(8, Sign.NAUGHT);
    board.markASign(15, Sign.CROSS);


    //then
    soft.assertTrue(boardChecker.isWinner(15, board));

    soft.assertAll();
  }

}

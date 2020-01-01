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

  public void shouldReturnTrueOnBoard() {
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
}

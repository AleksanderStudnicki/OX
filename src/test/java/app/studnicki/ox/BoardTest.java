package app.studnicki.ox;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class BoardTest {

  public void boardLimitShouldBeNineAfterCreationWithDimensionEqualsThree() {
    //given
    Board board;

    //when
    board = new Board(3);

    //then
    assertEquals(board.limit, 9);
  }

  public void boardSizeShouldBeTwo() {
    //given
    Board board = new Board(3);

    //when
    board.markASign(0, Sign.NAUGHT);
    board.markASign(1, Sign.CROSS);

    //then
    assertEquals(board.size(), 2);
  }


}

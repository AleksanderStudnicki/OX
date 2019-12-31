package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class BoardTest {

  public void boardLimitShouldBeNineAfterCreationWithDimensionEqualsThree() {
    //given
    Board board;

    //when
    board = new Board(3);

    //then
    Assert.assertEquals(board.limit, 9);
  }


}

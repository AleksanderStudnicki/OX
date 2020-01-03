package app.studnicki.ox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class RoundTest {
  public void dimensionValueShouldBeThreeAfterConstruction() {
    //given
    Round round;

    //when
    round = new Round(3);

    //then
    assertEquals(round.board.dimension, 3);
  }

  @Test(expectedExceptions = NotInBoardRangeException.class)
  public void shouldThrowNotInBoardRangeExceptionWhenFieldIdIsGreaterThanLimit()
      throws ExistingFieldException, NotInBoardRangeException {
    //given
    Round round = new Round(3);

    //when
    round.markASign(9, Sign.NAUGHT);

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = NotInBoardRangeException.class)
  public void shouldThrowNotInBoardRangeExceptionWhenFieldIdIsLesserThan0() {
    //given
    Round round = new Round(3);

    //when
    round.markASign(-1, Sign.NAUGHT);

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = ExistingFieldException.class)
  public void shouldThrowExistingFieldExceptionWhenFieldIsAlreadySet() {
    //given
    Round round = new Round(3);

    //when
    round.markASign(0, Sign.NAUGHT);
    round.markASign(0, Sign.CROSS);

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenRoundDimensionIsLesserThanThree() {
    //given
    Round round;

    //when
    round = new Round(0);

    //then
    //exception above method declaration
  }

}

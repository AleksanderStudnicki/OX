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
    assertEquals(round.dimension, 3);
  }

  @Test(expectedExceptions = IndexOutOfBoundsException.class)
  public void shouldThrowIndexOutOfBoundsExceptionWhenFieldIdIsGreaterThanLimit() {
    //given
    Round round = new Round(3);

    //when
    round.setField(9, Sign.NAUGHT);

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = IndexOutOfBoundsException.class)
  public void shouldThrowIndexOutOfBoundsExceptionWhenFieldIdIsLesserThan0() {
    //given
    Round round = new Round(3);

    //when
    round.setField(-1, Sign.NAUGHT);

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenFieldIsAlreadySet() {
    //given
    Round round = new Round(3);

    //when
    round.setField(0, Sign.NAUGHT);
    round.setField(0, Sign.CROSS);

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

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowNullPointerExceptionWhenSignInSetFieldIsNull() {
    //given
    Round round = new Round(3);

    //when
    round.setField(0, null);

    //then
    //exception above method declaration
  }
}

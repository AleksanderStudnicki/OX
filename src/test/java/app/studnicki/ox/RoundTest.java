package app.studnicki.ox;

import org.testng.Assert;
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

  @Test (expectedExceptions = IndexOutOfBoundsException.class)
  public void shouldThrowExceptionWhenFieldIsNotInRangeOfBoard() {
    //given
    Round round = new Round(3);

    //when
    round.setField(44, Sign.NAUGHT);

    //then
    //exception above method declaration
  }
}

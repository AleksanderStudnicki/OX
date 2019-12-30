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
}

package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ScoreTest {
  public void valueIsZeroAfterACreationOfANewScore() {
    //given
    Score score;

    //when
    score = new Score();

    //then
    assertEquals(score.value, 0);
  }
}

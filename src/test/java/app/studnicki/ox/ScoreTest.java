package app.studnicki.ox;

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

  public void valueAfterAdditionOfWinShouldBeEqualsThree() {
    //given
    Score score = new Score();

    //when
    score.addWin();

    //then
    assertEquals(score.value, 3);
  }

  public void valueAfterAdditionOfDrawShouldBeEqualsOne() {
    //given
    Score score = new Score();

    //when
    score.addDraw();

    //then
    assertEquals(score.value, 1);
  }

  @Test(expectedExceptions = VerifyError.class)
  public void exceptionShouldBeThrownBecauseValueWouldBeGreaterThanLimitAfterAdditionOfWin() {
    //given
    Score score = new Score();

    //when
    score.addWin();
    score.addWin();
    score.addWin();

    //then
    //exception above method declaration
  }

  @Test(expectedExceptions = VerifyError.class)
  public void exceptionShouldBeThrownBecauseValueWouldBeGreaterThanLimitAfterAdditionOfDraw() {
    //given
    Score score = new Score();

    //when
    //8 times
    score.addDraw();
    score.addDraw();
    score.addDraw();
    score.addDraw();
    score.addDraw();
    score.addDraw();
    score.addDraw();
    score.addDraw();

    //then
    //exception above method declaration
  }
}

package app.studnicki.ox;

//STATIC

import static org.testng.Assert.assertEquals;

//NON STATIC

import org.testng.annotations.Test;


@Test
public class ScoreTest {

  /**
   * Score class has addWin() method that adds 3 to value of the score
   * after each win by a player.
   * So after the first win, when player had 0 points at the beginning,
   * it should return 3 as a value of the score.
   * (win - 3 points, draw - 1 point, lose - 0 points)
   */

  public void addedWinToScoreShouldSetAValueToThree() {
    Score score = new Score();

    score.addWin();

    assertEquals(score.value, 3);
  }

  public void addedDrawToScoreShouldSetAValueToOne() {
    Score score = new Score();

    score.addDraw();

    assertEquals(score.value, 1);
  }

  @Test(expectedExceptions = VerifyError.class)
  public void shouldThrowExceptionWhenValueIsHigherThanSixByAddingWins() {
    Score score = new Score();

    score.addWin();
    score.addWin();
    score.addWin();
  }

  @Test(expectedExceptions = VerifyError.class)
  public void shouldThrowExceptionWhenValueIsHigherThanSixByAddingDraws() {
    Score score = new Score();

    score.addWin();
    score.addWin();
    score.addDraw();
  }

}

package app.studnicki.ox;

import app.studnicki.ox.game.Score;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Test
public class ScoreTest {

  public void addedWinToScoreShouldSetAValueToThree() {
    //given
    Score score = new Score();

    //when
    score.addWin();

    //then
    assertEquals(score.value, 3);
  }

  public void addedDrawToScoreShouldSetAValueToOne() {
    //given
    Score score = new Score();


    //when
    score.addDraw();


    //then
    assertEquals(score.value, 1);
  }

  @Test(expectedExceptions = VerifyError.class)
  public void shouldThrowExceptionWhenValueIsHigherThanSixByAddingWins() {
    //given
    Score score = new Score();

    //when
    score.addWin();
    score.addWin();
    score.addWin();

    //then (expectedExceptions above)
  }

  @Test(expectedExceptions = VerifyError.class)
  public void shouldThrowExceptionWhenValueIsHigherThanSixByAddingDraws() {
    //given
    Score score = new Score();


    //when
    score.addWin();
    score.addWin();
    score.addDraw();

    //then (expectedExceptions above)
  }

  public void scoreWithValueEqualsFiveShouldReturnStringFiveOnToString() {
    //given
    Score score = new Score();


    //when
    score.addWin();
    score.addDraw();
    score.addDraw();

    //then
    assertEquals(score.toString(), "5");
  }

}

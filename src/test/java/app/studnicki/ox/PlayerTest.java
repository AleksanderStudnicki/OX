package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class PlayerTest {
  public void nameOfThePlayerAfterCreationShouldBeAleksander() {
    //given
    Player player;

    //when
    player = new Player("Aleksander");

    //then
    assertEquals(player.name, "Aleksander");
  }

  public void shouldReturnFalseOnHasWonBecauseScoreOfPlayerIsLessThanFive() {
    //given
    Player player = new Player("Aleksander");

    //when - score will be equals 3
    player.addWin();

    //then
    assertFalse(player.hasWon());
  }

  public void shouldReturnTrueOnHasWonBecauseScoreOfPlayerIsEqualsFive() {
    //given
    Player player = new Player("Aleksander");

    //when - score will be equals 5
    player.addWin();
    player.addDraw();
    player.addDraw();

    //then
    assertTrue(player.hasWon());
  }

  public void shouldReturnTrueOnHasWonBecauseScoreOfPlayerIsGreaterThanFive() {
    //given
    Player player = new Player("Aleksander");

    //when - score will be equals 6
    player.addWin();
    player.addWin();

    //then
    assertTrue(player.hasWon());
  }

  public void shouldReturnFalseOnHasWonBecauseScoreOfPlayerIsEqualsZero() {
    //given
    Player player = new Player("Aleksander");

    //when - score will be equals 0

    //then
    assertFalse(player.hasWon());
  }
}

package app.studnicki.ox;

import org.testng.annotations.Test;

@Test
public class GameTest {

  @Test (expectedExceptions = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenWinningRuleIsGreaterThanDimension() {
    //given
    Game.Builder builder = new Game.Builder();

    //when
    builder.player1(new Player("Aleksander", Sign.NAUGHT))
        .player2(new Player("Czesio", Sign.CROSS))
        .winningRule(4)
        .dimension(3)
        .userInterface(null);

    //then
    builder.build();
  }
}

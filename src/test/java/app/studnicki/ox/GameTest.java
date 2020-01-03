package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test
public class GameTest {

  public void shouldThrowOnDisallowedGameOptions() {
    //given
    SoftAssert soft = new SoftAssert();
    Game.Builder builder = new Game.Builder();

    //when
    builder.player1(new Player("Aleksander", Sign.NAUGHT))
        .player2(new Player("Czesio", Sign.CROSS))
        .winningRule(4)
        .dimension(3)
        .userInterface(null);

    //then
    try {
      builder.build();
    } catch (IllegalArgumentException ex) {
      soft.assertEquals(ex.getClass(), IllegalArgumentException.class);
      soft.assertEquals(ex.getMessage(),  Config.INSTANCE.getMessage(MessageKey.WRONG_WINNING_RULE));
      soft.assertAll();
    }
  }
}

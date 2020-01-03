package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;

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
      soft.assertEquals(ex.getMessage(), Config.INSTANCE.getMessage(MessageKey.WRONG_WINNING_RULE));
      soft.assertAll();
    }
  }

  public void shouldReturnNewGameOnProperGameOptions() {
    //given
    SoftAssert soft = new SoftAssert();
    Game.Builder builder = new Game.Builder();
    Player player1 = new Player("Aleksander", Sign.NAUGHT);
    Player player2 = new Player("Czesio", Sign.CROSS);

    //when
    builder.player1(player1)
        .player2(player2)
        .winningRule(3)
        .dimension(3)
        .userInterface(null);
    Game game = builder.build();

    //then
    soft.assertEquals(game.player1, player1);
    soft.assertEquals(game.player2, player2);
    soft.assertEquals(game.dimension, 3);
    soft.assertAll();
  }

  public void gameShouldEndedWithFirstPlayerHavingSixPoints() {
    //given
    String input = "0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n"
        + "3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);
    Player player1 = new Player("Aleksander", Sign.NAUGHT);
    Player player2 = new Player("Czesio", Sign.CROSS);
    Game game = new Game.Builder()
        .player1(player1)
        .player2(player2)
        .winningRule(3)
        .dimension(3)
        .userInterface(consoleUserInterface)
        .build();
    SoftAssert soft = new SoftAssert();

    //when
    game.start();

    //then
    soft.assertEquals(player1.score.value, 6);
    soft.assertEquals(player2.score.value, 3);
    soft.assertAll();
  }

  public void shouldBeDrawWith4Points() {
    //given
    String input = "0\n1\n2\n3\n4\n5\n6\n\n"
        + "0\n1\n2\n3\n4\n5\n6\n\n"
        + "0\n1\n2\n4\n3\n6\n5\n8\n7\n\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);
    Player player1 = new Player("Aleksander", Sign.NAUGHT);
    Player player2 = new Player("Czesio", Sign.CROSS);
    Game game = new Game.Builder()
        .player1(player1)
        .player2(player2)
        .winningRule(3)
        .dimension(3)
        .userInterface(consoleUserInterface)
        .build();
    SoftAssert soft = new SoftAssert();

    //when
    game.start();

    //then
    soft.assertEquals(player1.score.value, 4);
    soft.assertEquals(player2.score.value, 4);
    soft.assertAll();
  }
}

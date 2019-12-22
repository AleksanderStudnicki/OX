package app.studnicki.ox.ui;

import app.studnicki.ox.Sign;
import app.studnicki.ox.game.PlayerBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@Test
public class ConsoleUserInterfaceTest {

  public void test() {
    //given
    PlayerBuilder pb = new PlayerBuilder();
    PlayerBuilder pb2 = new PlayerBuilder();

    ConsoleUserInterface ui = new ConsoleUserInterface();
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    System.setOut(new PrintStream(os));


    pb.name("Aleksander")
        .sign(Sign.O);
    pb2.name("Czesio")
        .sign(Sign.X);

    //when
    ui.playersHeader(pb.build(), pb2.build());

    //then
    Assert.assertEquals(os.toString(), "Player #1 - Aleksander: 0  |  Player #2 - Czesio: 0\n");
  }
}

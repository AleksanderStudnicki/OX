package app.studnicki.ox;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


@Test
public class ConsoleUserInterfaceTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeClass
  public void setStreams() {
    System.setOut(new PrintStream(out));
  }

  @AfterClass
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @AfterMethod
  public void cleanStream() {
    out.reset();
  }

  public void shouldPrintWelcomeMessageInEnglish() {
    //given
    ConsoleUserInterface ui = new ConsoleUserInterface();

    //when
    ui.welcome();

    //then
    assertEquals(out.toString().trim(), Config.INSTANCE.getString("welcome"));
  }

  public void shouldPrintBoardWithNaughtAtTheFirstField() {
    //given
    Round round = new Round(3);
    ConsoleUserInterface ui = new ConsoleUserInterface();
    round.addObserver(ui);

    //when
    round.setField(0, Sign.NAUGHT);

    //then
    String expected = "\033[H\033[2J  -------------\n" +
        "0 | O |   |   |\n" +
        "  -------------\n" +
        "1 |   |   |   |\n" +
        "  -------------\n" +
        "2 |   |   |   |\n" +
        "  -------------\n";

    assertEquals(out.toString(), expected);
  }
}

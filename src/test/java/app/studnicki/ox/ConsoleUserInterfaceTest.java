package app.studnicki.ox;

import static app.studnicki.ox.MessageKey.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@Test
public class ConsoleUserInterfaceTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final ByteArrayOutputStream err = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;
  private final String clearCommand = "\033[H\033[2J";

  @BeforeClass
  public void setStreams() {
    System.setOut(new PrintStream(out));
    System.setErr(new PrintStream(err));
  }

  @AfterClass
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @AfterMethod
  public void cleanStream() {
    out.reset();
    err.reset();
  }

  public void shouldPrintWelcomeMessageInEnglish() {
    //given
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    //when
    ui.welcome();

    //then
    assertEquals(out.toString(), clearCommand + Config.INSTANCE.getMessage(WELCOME) + "\n");
  }

  public void shouldPrintWelcomeMessageInPolish() {
    //given
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    //when
    Config.INSTANCE.changeLanguage("pl");
    ui.welcome();

    //then
    assertEquals(out.toString(), clearCommand + Config.INSTANCE.getMessage(WELCOME) + "\n");
  }

  public void shouldPrintBoardWithNaughtAtTheFirstField() {
    //given
    Round round = new Round(3);
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);
    round.addObserver(ui);

    //when
    round.setField(0, Sign.NAUGHT);

    //then
    String expected = clearCommand +
        "  -------------\n" +
        "0 | O |   |   |\n" +
        "  -------------\n" +
        "1 |   |   |   |\n" +
        "  -------------\n" +
        "2 |   |   |   |\n" +
        "  -------------\n";

    assertEquals(out.toString(), expected);
  }

  public void shouldPrintErrorStringOnErrorUIMethod() {
    //given
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);

    //when
    consoleUserInterface.error("Error");

    //then
    assertEquals(err.toString(), "Error\n");
  }
}

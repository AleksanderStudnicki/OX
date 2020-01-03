package app.studnicki.ox;

import static app.studnicki.ox.Config.INSTANCE;
import static app.studnicki.ox.MessageKey.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
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
    INSTANCE.changeLanguage("en");
  }

  public void shouldPrintBoardWithNaughtAtTheFirstField() {
    //given
    Round round = new Round(3);
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);
    round.addListenerForBoardPrinting(ui);

    //when
    round.markASign(0, Sign.NAUGHT);

    //then
    String expected = clearCommand
        + "-------------\n"
        + "| O | 1 | 2 |\n"
        + "-------------\n"
        + "| 3 | 4 | 5 |\n"
        + "-------------\n"
        + "| 6 | 7 | 8 |\n"
        + "-------------\n";

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

  public void shouldShowsProperMessageOnWaitOnAction() {
    //given
    ConsoleUserInterface consoleUserInterface
        = new ConsoleUserInterface(new ByteArrayInputStream("\n".getBytes()));

    //when
    consoleUserInterface.waitForAnyAction();

    //then
    assertEquals(out.toString(), "Press ENTER to continue...\n");
  }

  public void shouldReturnProperMessageOnAnnounceAWinnerMethod() {
    //given
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);
    Player player = new Player("Aleksander", Sign.NAUGHT);

    //when
    consoleUserInterface.announceWinner(player);

    //then
    String expectedMessage = String.format(INSTANCE.getMessage(CONGRATULATIONS)
        + " %s. " + INSTANCE.getMessage(YOU_WIN) + "%n"
        + INSTANCE.getMessage(YOUR_SCORE) + "%n", player.name, player.score);

    assertEquals(out.toString(), expectedMessage);
  }

  public void shouldShowsProperMessageOnDraw() {
    //given
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);

    //when
    consoleUserInterface.announceDraw();

    //then
    assertEquals(out.toString(), INSTANCE.getMessage(DRAW) + "\n");
  }

  public void shouldShowNotInRangeMessageOnInputGreaterThanLimit() {
    //given
    String input = "10\n\n3\n";
    ConsoleUserInterface consoleUserInterface
        = new ConsoleUserInterface(new ByteArrayInputStream(input.getBytes()));

    //when
    consoleUserInterface.fieldId(9);

    //then
    assertEquals(err.toString(), INSTANCE.getMessage(NOT_IN_RANGE) + "\n");
  }

  public void shouldShowNotInRangeMessageOnInputLesserThanZero() {
    //given
    String input = "-2\n\n3\n";
    ConsoleUserInterface consoleUserInterface
        = new ConsoleUserInterface(new ByteArrayInputStream(input.getBytes()));

    //when
    consoleUserInterface.fieldId(9);

    //then
    assertEquals(err.toString(), INSTANCE.getMessage(NOT_IN_RANGE) + "\n");
  }

  public void shouldShowWrongMenuInputMessageOnNotProperInput() {
    //given
    String input = "asdadasd\n\n3\n";
    ConsoleUserInterface consoleUserInterface
        = new ConsoleUserInterface(new ByteArrayInputStream(input.getBytes()));

    //when
    consoleUserInterface.fieldId(9);

    //then
    assertEquals(err.toString(), INSTANCE.getMessage(WRONG_MENU_INPUT) + "\n");
  }

  public void shouldNotActOnNotProperPropertyEventChange() {
    //given
    ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface(System.in);
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    pcs.addPropertyChangeListener(consoleUserInterface);

    //when
    pcs.firePropertyChange("notMarkedAField", 0, 1);

    //then
    assertEquals(out.toString().length(), 0);
  }
}

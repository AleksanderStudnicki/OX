package app.studnicki.ox;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
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

  public void shouldPrintWelcomeMessageInEnglish() {
    //given
    ConsoleUserInterface ui = new ConsoleUserInterface();

    //when
    ui.welcome();

    //then
    assertEquals(out.toString().trim(), Config.INSTANCE.getString("welcome"));
  }
}

package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

import static app.studnicki.ox.Config.INSTANCE;
import static app.studnicki.ox.MessageKey.*;


class ConsoleUserInterface implements UserInterface {

  private final InputStream inputStream;

  ConsoleUserInterface(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  /**
   * Prints board when property equals filledField (because then source is the Round class object).
   *
   * @param evt event from external source.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("markedAField")) {
      Board board = (Board) evt.getSource();
      board(board);
    }
  }

  /**
   * Prints welcome message (from properties file) with endline
   * on a console using standard output print stream.
   */
  @Override
  public void welcome() {
    clear();
    System.out.println(INSTANCE.getMessage(WELCOME));
  }

  /**
   * Prints board to a console output print stream.
   *
   * @param board filled fields in board represented as a map
   */
  @Override
  public void board(Board board) {
    clear();
    showSeparationLine(board.dimension);
    IntStream.range(0, board.dimension)
        .forEach(row -> {
          showLine(board.dimension, row, board);
          showSeparationLine(board.dimension);
        });
  }

  /**
   * Returns id of the field.
   *
   * @param limit any number must be lesser than that number
   * @return returns valid user input
   */
  @Override
  public int fieldId(int limit) {
    Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    System.out.println(INSTANCE.getMessage(FIELD_ID));
    try {
      int id = scanner.nextInt();
      if (id < 0 || id >= limit) {
        System.err.println(INSTANCE.getMessage(NOT_IN_RANGE));
        return fieldId(limit);
      }
      return id;
    } catch (InputMismatchException ex) {
      System.err.println(INSTANCE.getMessage(WRONG_MENU_INPUT));
      return fieldId(limit);
    }
  }

  /**
   * Prints a message as an error.
   *
   * @param content Content of error message
   */
  @Override
  public void error(String content) {
    System.err.println(content);
  }

  @Override
  public void announceWinner(Player player) {
    System.out.println("Congratulations " + player.name + ". You Win!");
    System.out.println("Your score was: " + player.score);
  }

  @Override
  public void nowPlaying(Player player) {
    System.out.println("Now playing: " + player.name);
  }

  @Override
  public void waitForAnyAction() {
    System.out.println("Press ENTER to continue...");
    Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    scanner.nextLine();
  }

  @Override
  public void announceResultOfRound(Player player1, Player player2) {
    System.out.println(player1 + "  |  " + player2);
  }

  /**
   * String passed to print method is a command for cleaning console
   * (same effect as clear command, printing as many empty lines as required
   * to make empty console effect).
   */
  private void clear() {
    System.out.print("\033[H\033[2J");
  }

  private void showLine(int totalRow, int actualRow, Board board) {
    int start = (actualRow * totalRow);
    int end = (actualRow * totalRow) + totalRow;

    System.out.printf("%d |", actualRow);

    IntStream.range(start, end)
        .forEach(index -> board.getSignFromField(index).ifPresentOrElse(
            s -> System.out.printf(" %s |", s),
            () -> System.out.print("   |")
        ));

    System.out.println();
  }

  private void showSeparationLine(int n) {
    System.out.print("  -");
    IntStream.range(0, n).forEach(i -> System.out.print("----"));
    System.out.println();
  }

}

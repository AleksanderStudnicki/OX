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

  Scanner scanner;

  ConsoleUserInterface(InputStream inputStream) {
    scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
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
    System.out.println(INSTANCE.getMessage(FIELD_ID));
    try {
      int id = scanner.nextInt();
      scanner.nextLine();
      if (id < 0 || id >= limit) {
        System.err.println(INSTANCE.getMessage(NOT_IN_RANGE));
        scanner.nextLine();
        return fieldId(limit);
      }
      return id;
    } catch (InputMismatchException ex) {
      System.err.println(INSTANCE.getMessage(WRONG_MENU_INPUT));
      scanner.nextLine();
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

  /**
   * Shows winner message.
   *
   * @param player Winner or game.
   */
  @Override
  public void announceWinner(Player player) {
    System.out.println(INSTANCE.getMessage(CONGRATULATIONS)
        + " " + player.name + ". " + INSTANCE.getMessage(YOU_WIN));
    System.out.printf(INSTANCE.getMessage(YOUR_SCORE), player.score);
    System.out.println();
  }

  @Override
  public void announceDraw() {
    System.out.println(INSTANCE.getMessage(DRAW));
  }

  /**
   * Shows which player should mark a field.
   *
   * @param player Current player
   */
  @Override
  public void nowPlaying(Player player) {
    System.out.println(INSTANCE.getMessage(NOW_PLAYING) + player.name);
  }

  /**
   * Waits for pressing ENTER key.
   */
  @Override
  public void waitForAnyAction() {
    System.out.println(INSTANCE.getMessage(PRESS_ENTER));
    scanner.nextLine();
  }

  /**
   * Shows summary of players and their scores after the round.
   *
   * @param player1 first player
   * @param player2 second player
   */
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

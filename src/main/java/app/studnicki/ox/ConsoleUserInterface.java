package app.studnicki.ox;

import static app.studnicki.ox.Config.INSTANCE;
import static app.studnicki.ox.Message.*;

import java.beans.PropertyChangeEvent;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;


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
    if (evt.getPropertyName().equals("filledField")) {
      Round round = (Round) evt.getSource();
      board(round.board.dimension, round.board.getMap());
    }
  }

  /**
   * Prints welcome message (from properties file) with endline
   *                  on a console using standard output print stream.
   */
  @Override
  public void welcome() {
    clear();
    System.out.println(INSTANCE.getString(WELCOME));
  }

  /**
   * Prints board to a console output print stream.
   *
   * @param n     amount of field on one row
   * @param board filled fields in board represented as a map
   */
  @Override
  public void board(int n, Map<Integer, Sign> board) {
    clear();
    showSeparationLine(n);
    IntStream.range(0, n)
        .forEach(row -> {
          showLine(n, row, board);
          showSeparationLine(n);
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
    System.out.println(INSTANCE.getString(FIELD_ID));
    try {
      int id = scanner.nextInt();
      if (id < 0 || id >= limit) {
        System.out.println(INSTANCE.getString(NOT_IN_RANGE));
        return fieldId(limit);
      }
      return id;
    } catch (InputMismatchException ex) {
      System.out.println(INSTANCE.getString(WRONG_MENU_INPUT));
      return fieldId(limit);
    }
  }

  /**
   * Prints a message as an error.
   * @param content Content of error message
   */
  @Override
  public void error(String content) {
    System.err.println(content);
  }

  private void clear() {
    System.out.print("\033[H\033[2J");
  }

  private void showLine(int totalRow, int actualRow, Map<Integer, Sign> board) {
    int start = (actualRow * totalRow);
    int end = (actualRow * totalRow) + totalRow;

    System.out.printf("%d |", actualRow);

    IntStream.range(start, end)
        .forEach(index -> {
          if (board.containsKey(index)) {
            System.out.printf(" %s |", board.get(index));
          } else {
            System.out.print("   |");
          }
        });

    System.out.println();
  }

  private void showSeparationLine(int n) {
    System.out.print("  -");
    IntStream.range(0, n).forEach(i -> System.out.print("----"));
    System.out.println();
  }

}

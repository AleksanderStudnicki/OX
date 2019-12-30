package app.studnicki.ox;

import static app.studnicki.ox.Config.INSTANCE;

import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsoleUserInterface implements UserInterface {
  /**
   * Prints board when property equals filledField (because then source is the Round class object).
   *
   * @param evt event from external source.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("filledField")) {
      Round round = (Round) evt.getSource();
      board(round.dimension, round.board);
    }
  }

  /**
   * Prints welcome message (from properties file) with endline on a console using standard output print stream.
   */
  @Override
  public void welcome() {
    System.out.println(INSTANCE.getString("welcome"));
  }

  /**
   * Prints board to a console output print stream.
   *
   * @param n     amount of field on one row
   * @param board filled fields in board represented as a map
   */
  @Override
  public void board(int n, Map<Integer, Sign> board) {
    //TODO: implementation
    clear();
    showSeparationLine(n);
    IntStream.range(0, n)
        .forEach(row -> {
          showLine(n, row, board);
          showSeparationLine(n);
        });
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

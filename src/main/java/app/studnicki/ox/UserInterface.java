package app.studnicki.ox;

import java.beans.PropertyChangeListener;
import java.util.Map;

public interface UserInterface extends PropertyChangeListener {
  /**
   * Should print/show welcome message, based on implementation.
   */
  void welcome();

  /**
   * Should print/show game board, based on implementation.
   * @param n     amount of field on one row
   * @param board filled fields in board represented as a map
   */
  void board(int n, Map<Integer, Sign> board);

  int fieldId(int limit);

  void error(String content);
}

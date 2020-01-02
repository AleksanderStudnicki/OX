package app.studnicki.ox;

import java.beans.PropertyChangeListener;

public interface UserInterface extends PropertyChangeListener {
  /**
   * Should print/show welcome message, based on implementation.
   */
  void welcome();

  /**
   * Should print/show game board, based on implementation.
   * @param board filled fields in board represented as a map
   */
  void board(Board board);

  int fieldId(int limit);

  void error(String content);
}

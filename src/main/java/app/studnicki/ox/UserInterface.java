package app.studnicki.ox;

import java.beans.PropertyChangeListener;
import java.util.Map;

public interface UserInterface extends PropertyChangeListener {
  /**
   * Should print/show welcome message, based on implementation.
   */
  void welcome();

  void board(int n, Map<Integer, Sign> board);
}

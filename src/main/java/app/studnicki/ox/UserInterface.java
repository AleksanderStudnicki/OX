package app.studnicki.ox;

import java.beans.PropertyChangeListener;

public interface UserInterface extends PropertyChangeListener {
  /**
   * Should print/show welcome message, based on implementation.
   */
  void welcome();
}

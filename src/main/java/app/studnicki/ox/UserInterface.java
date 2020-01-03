package app.studnicki.ox;

import java.beans.PropertyChangeListener;

/**
 * Interface for showing the states of the whole application.
 * This interface also extends PropertyChangeListener to be able to react to
 * other classes actions like marking a field. Also there is a set of methods
 * to showing informations from outside and to get input from user.
 *
 * @author Aleksander Studnicki
 */
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

  void announceWinner(Player player);

  void nowPlaying(Player player);

  void waitForAnyAction();

  void announceResultOfRound(Player player1, Player player2);
}

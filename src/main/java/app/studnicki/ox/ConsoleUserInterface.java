package app.studnicki.ox;

import static app.studnicki.ox.Config.INSTANCE;

import java.beans.PropertyChangeEvent;
import java.util.Map;

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
   * @param n     amount of field on one line
   * @param board filled fields in board represented as a map
   */
  @Override
  public void board(int n, Map<Integer, Sign> board) {
    //TODO: implementation
  }


}

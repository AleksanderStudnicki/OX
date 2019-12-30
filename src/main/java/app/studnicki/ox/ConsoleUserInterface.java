package app.studnicki.ox;

import static app.studnicki.ox.Config.INSTANCE;

import java.beans.PropertyChangeEvent;

public class ConsoleUserInterface implements UserInterface {
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    //TODO: implementation of that method
  }

  /**
   * Prints welcome message (from properties file) with endline on a console using standard output print stream.
   */
  @Override
  public void welcome() {
    System.out.println(INSTANCE.getString("welcome"));
  }
}

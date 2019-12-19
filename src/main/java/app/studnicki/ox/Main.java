package app.studnicki.ox;

import app.studnicki.ox.ui.UserInterface;
import app.studnicki.ox.ui.UserInterfaceFactory;

public class Main {
  public static void main(String[] args) {
    UserInterface ui = UserInterfaceFactory.console();

    ui.welcome();
    ui.menu();
  }
}

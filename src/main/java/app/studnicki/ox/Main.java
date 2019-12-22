package app.studnicki.ox;

import app.studnicki.ox.config.Config;
import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;
import app.studnicki.ox.ui.UserInterfaceFactory;
import app.studnicki.ox.initstate.InitState;
import app.studnicki.ox.initstate.InitStateFactory;

public class Main {
  public static void main(String[] args) {
    UserInterface ui = UserInterfaceFactory.console();
    Menu menu = new Menu(ui);
    menu.run();
  }

}

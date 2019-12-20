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

    while (true) {
      ui.welcome();

      int choice = ui.menu();

      if (choice == 2) {
        switch (ui.changeLanguage()) {
          case 1:
            Config.getInstance().changeLanguage("en");
            break;
          case 2:
            Config.getInstance().changeLanguage("pl");
            break;
          case 3:
            Config.getInstance().changeLanguage("ja");
            break;
          case 0:
            break;
        }
      } else if (choice == 1) {
        GameBuilder gameBuilder = new GameBuilder();
        InitState initState = InitStateFactory.start();

        initState.run(gameBuilder, ui);


      }

    }

  }

}

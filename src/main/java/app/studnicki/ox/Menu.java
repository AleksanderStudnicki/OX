package app.studnicki.ox;

import app.studnicki.ox.config.Config;
import app.studnicki.ox.game.Game;
import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.initstate.InitState;
import app.studnicki.ox.initstate.InitStateFactory;
import app.studnicki.ox.ui.UserInterface;

class Menu {

  private final UserInterface ui;
  private boolean exit;

  Menu(UserInterface ui) {
    this.ui = ui;
  }

  void run() {
    while (!exit) {
      ui.welcome();

      int choice = ui.menu();

      switch (choice) {
        case 1:
          runGame();
          break;
        case 2:
          runLanguageMenu();
          break;
        case 0:
          exit = true;
          break;
      }
    }
  }

  private void runLanguageMenu() {
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
  }

  private void runGame() {
    GameBuilder gameBuilder = new GameBuilder();
    gameBuilder.userInterface(ui);

    InitState initState = InitStateFactory.start(gameBuilder, ui);

    while (initState != null) {
      initState = initState.run();
    }

    Game game = gameBuilder.build();

    game.play();
  }
}

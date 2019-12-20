package app.studnicki.ox.game.gamestate;

import app.studnicki.ox.ui.UserInterface;

public class PlayersInitState implements GameState {

  UserInterface ui;

  public PlayersInitState(UserInterface userInterface) {
    ui = userInterface;
  }

  @Override
  public GameState run() {
    return new BoardSizeInit();
  }
}

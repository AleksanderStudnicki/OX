package app.studnicki.ox.game.gamestate;

import app.studnicki.ox.game.Game;
import app.studnicki.ox.ui.UserInterface;

public class PlayersInitState implements GameState {

  Game game;

  public PlayersInitState(Game game) {
    this.game = game;
  }

  @Override
  public GameState run() {
    return new BoardSizeInit();
  }
}

package app.studnicki.ox.game.gamestate;

import app.studnicki.ox.game.Game;
import app.studnicki.ox.ui.UserInterface;

public class GameStateFactory {

  public static GameState start(Game game) {
    return new PlayersInitState(game);
  }
}

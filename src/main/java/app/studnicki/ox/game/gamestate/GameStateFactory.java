package app.studnicki.ox.game.gamestate;

import app.studnicki.ox.game.Game;

public class GameStateFactory {

  public static GameState start(Game game) {
    return new PlayersInitState(game);
  }
}

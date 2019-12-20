package app.studnicki.ox.game;

import app.studnicki.ox.game.gamestate.GameState;
import app.studnicki.ox.game.gamestate.PlayersInitState;
import app.studnicki.ox.ui.UserInterface;

public class Game {

  private GameState gameState;

  Game(UserInterface userInterface) {
    gameState = new PlayersInitState(userInterface);
  }

  void play() {
    while (gameState != null) {
      gameState = gameState.run();
    }
  }
}

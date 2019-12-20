package app.studnicki.ox.game;

import app.studnicki.ox.game.gamestate.GameState;
import app.studnicki.ox.game.gamestate.GameStateFactory;
import app.studnicki.ox.ui.UserInterface;

public class Game {

  private final UserInterface userInterface;
  private final Player firstPlayer;
  private final Player secondPlayer;
  private final WinningRule winningRule;

  private GameState gameState;

  Game(GameBuilder builder) {
    userInterface = builder.userInterface;
    firstPlayer = builder.firstPlayer;
    secondPlayer = builder.secondPlayer;
    winningRule = builder.winningRule;
  }

  void play() {
    while (gameState != null) {
      gameState = gameState.run();
    }
  }
}

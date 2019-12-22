package app.studnicki.ox.game;

import app.studnicki.ox.game.gamestate.GameState;
import app.studnicki.ox.ui.UserInterface;

public class Game {

  private final UserInterface userInterface;
  private final Player firstPlayer;
  private final Player secondPlayer;
  private final int winningRule;
  private final int dimension;

  private GameState gameState;

  Game(GameBuilder builder) {
    userInterface = builder.userInterface;
    firstPlayer = builder.firstPlayer;
    secondPlayer = builder.secondPlayer;
    winningRule = builder.winningRule;
    dimension = builder.dimension;
  }

  public void play() {
    Round round = new Round(userInterface, firstPlayer, secondPlayer, dimension, winningRule);
    round.play();
  }

}

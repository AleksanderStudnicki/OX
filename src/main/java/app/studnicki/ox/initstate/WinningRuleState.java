package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

public class WinningRuleState implements InitState {

  GameBuilder gameBuilder;
  UserInterface userInterface;
  int limit;

  WinningRuleState(GameBuilder gameBuilder, UserInterface userInterface, int limit) {
    this.gameBuilder = gameBuilder;
    this.userInterface = userInterface;
    this.limit = limit;
  }

  @Override
  public InitState run() {
    int winningRule = userInterface.typeWinningRule(limit);
    gameBuilder.winningRule(winningRule);
    return null;
  }
}

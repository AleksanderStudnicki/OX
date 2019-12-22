package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

public class DimensionsState implements InitState {

  GameBuilder gameBuilder;
  UserInterface userInterface;

  DimensionsState(GameBuilder gameBuilder, UserInterface userInterface) {
    this.gameBuilder = gameBuilder;
    this.userInterface = userInterface;
  }


  @Override
  public InitState run() {
    int dimension = userInterface.typeDimension();
    gameBuilder.dimension(dimension);
    return new WinningRuleState(gameBuilder, userInterface, dimension);
  }
}

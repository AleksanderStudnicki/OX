package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

class SecondPlayerInitState implements InitState {

  GameBuilder gameBuilder;
  UserInterface userInterface;

  SecondPlayerInitState(GameBuilder gameBuilder, UserInterface userInterface) {
    this.gameBuilder = gameBuilder;
    this.userInterface = userInterface;
  }

  @Override
  public InitState run() {
    String name = userInterface.typeSecondName();
    gameBuilder.secondPlayerName(name);
    return new DimensionsState(gameBuilder, userInterface);
  }
}

package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

class FirstPlayerInitState implements InitState {

  GameBuilder gameBuilder;
  UserInterface userInterface;

  FirstPlayerInitState(GameBuilder gameBuilder, UserInterface userInterface) {
    this.gameBuilder = gameBuilder;
    this.userInterface = userInterface;
  }

  @Override
  public InitState run() {
    String name = userInterface.typeFirstName();
    gameBuilder.firstPlayerName(name);
    return new FirstPlayerSignInit(gameBuilder, userInterface);
  }
}

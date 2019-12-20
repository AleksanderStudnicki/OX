package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

class FirstPlayerInitState implements InitState {

  @Override
  public InitState run(GameBuilder gameBuilder, UserInterface userInterface) {
    String name = userInterface.typeFirstName();
    gameBuilder.firstPlayerName(name);
    return new FirstPlayerSignInit().run(gameBuilder, userInterface);
  }
}

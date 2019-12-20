package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

public class InitStateFactory {

  public static InitState start() {
    return new FirstPlayerInitState();
  }
}

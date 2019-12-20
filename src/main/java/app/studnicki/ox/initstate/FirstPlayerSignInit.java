package app.studnicki.ox.initstate;

import app.studnicki.ox.Sign;
import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

class FirstPlayerSignInit implements InitState {
  @Override
  public InitState run(GameBuilder gameBuilder, UserInterface userInterface) {
    Sign sign = userInterface.typeSignOfFirstPlayer();
    gameBuilder.firstPlayerSign(sign);
    return new SecondPlayerInitState().run(gameBuilder, userInterface);
  }
}

package app.studnicki.ox.initstate;

import app.studnicki.ox.Sign;
import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

class FirstPlayerSignInit implements InitState {

  GameBuilder gameBuilder;
  UserInterface userInterface;

  FirstPlayerSignInit(GameBuilder gameBuilder, UserInterface userInterface) {
    this.gameBuilder = gameBuilder;
    this.userInterface = userInterface;
  }

  @Override
  public InitState run() {
    Sign sign = userInterface.typeSignOfFirstPlayer();
    gameBuilder.firstPlayerSign(sign);
    return new SecondPlayerInitState(gameBuilder, userInterface);
  }
}

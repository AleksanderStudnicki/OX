package app.studnicki.ox.initstate;

import app.studnicki.ox.game.GameBuilder;
import app.studnicki.ox.ui.UserInterface;

public interface InitState {
  InitState run(GameBuilder gameBuilder, UserInterface userInterface);
}

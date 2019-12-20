package app.studnicki.ox.game;

import app.studnicki.ox.Sign;

public class Player {

  private final Score score;
  private final String name;
  private final Sign sign;

  Player(PlayerBuilder builder) {
    score = new Score();
    name = builder.name;
    sign = builder.sign;
  }

  void addWin() {
    score.addWin();
  }

  void addDraw() {
    score.addDraw();
  }

  @Override
  public String toString() {
    return name + ": " + score;
  }
}

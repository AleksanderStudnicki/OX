package app.studnicki.ox.game;

import app.studnicki.ox.Sign;

public class PlayerBuilder {

  String name;
  Sign sign;

  public PlayerBuilder name(String name) {
    this.name = name;
    return this;
  }

  public PlayerBuilder sign(Sign sign) {
    this.sign = sign;
    return this;
  }

  public Player build() {
    return new Player(this);
  }

}

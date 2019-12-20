package app.studnicki.ox.game;

import app.studnicki.ox.Sign;

public class PlayerBuilder {

  String name;
  Sign sign;

  PlayerBuilder name(String name) {
    this.name = name;
    return this;
  }

  PlayerBuilder sign(Sign sign) {
    this.sign = sign;
    return this;
  }

  Player build() {
    return new Player(this);
  }

}

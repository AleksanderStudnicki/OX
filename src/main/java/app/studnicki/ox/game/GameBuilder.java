package app.studnicki.ox.game;


import app.studnicki.ox.Sign;
import app.studnicki.ox.ui.UserInterface;

public class GameBuilder {

  private final PlayerBuilder firstPlayerBuilder = new PlayerBuilder();
  private final PlayerBuilder secondPlayerBuilder = new PlayerBuilder();

  Player firstPlayer;
  Player secondPlayer;
  int winningRule;
  UserInterface userInterface;
  int dimension;

  public GameBuilder winningRule(int winningRule) {
    this.winningRule = winningRule;
    return this;
  }

  public GameBuilder firstPlayerName(String name) {
    firstPlayerBuilder.name(name);
    return this;
  }

  public GameBuilder secondPlayerName(String name) {
    secondPlayerBuilder.name(name);
    return this;
  }

  public GameBuilder firstPlayerSign(Sign sign) {
    firstPlayerBuilder.sign(sign);

    if (sign == Sign.O) {
      return secondPlayerSign(Sign.X);
    } else {
      return secondPlayerSign(Sign.O);
    }
  }

  private GameBuilder secondPlayerSign(Sign sign) {
    secondPlayerBuilder.sign(sign);
    return this;
  }

  public GameBuilder userInterface(UserInterface userInterface) {
    this.userInterface = userInterface;
    return this;
  }

  public GameBuilder dimension(int dimension) {
    this.dimension = dimension;
    return this;
  }

  public Game build() {
    this.firstPlayer = firstPlayerBuilder.build();
    this.secondPlayer = secondPlayerBuilder.build();

    return new Game(this);
  }
}

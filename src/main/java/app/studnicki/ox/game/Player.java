package app.studnicki.ox.game;

public class Player {

  private final Score score;
  private final String name;

  Player(String name) {
    score = new Score();
    this.name = name;
  }

  void addWin() {
    score.addWin();
  }

  void addDraw() {
    score.addDraw();
  }

  @Override
  public String toString() {
    return "Player{"
        + "score=" + score
        + ", name='" + name + '\''
        + '}';
  }
}

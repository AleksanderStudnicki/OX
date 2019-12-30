package app.studnicki.ox;

public class Player {

  public String name;
  private final Score score;

  public Player(String name) {
    this.name = name;
    score = new Score();
  }

  public void addWin() {
    score.addWin();
  }

  public boolean hasWon() {
    return score.value >= 5;
  }

  public void addDraw() {
    score.addDraw();
  }
}

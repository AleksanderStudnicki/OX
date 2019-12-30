package app.studnicki.ox;

public class Score {
  public int value;

  public void addWin() {
    value += 3;
  }

  public void addDraw() {
    value += 1;
  }
}

package app.studnicki.ox.game;

public class Score {
  public int value;

  private static final int WIN = 3;
  private static final int DRAW = 1;
  private static final int LIMIT = 6;

  public void addWin() {
    add(WIN);
  }

  public void addDraw() {
    add(DRAW);
  }

  private void add(int n) {
    if (value + n > LIMIT) {
      throw new VerifyError("Value cannot be greater than " + LIMIT);
    }
    value += n;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}

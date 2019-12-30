package app.studnicki.ox;

class Score {
  private int value;

  private static final int LIMIT = 7;
  private static final int WIN = 3;
  private static final int DRAW = 1;

  /**
   * Addition of 3 to value.
   * Win is represented by 3 points so that value should be added to score value.
   * If value after addition would be higher than a limit (7 points) exception will be thrown.
   */
  void addWin() {
    add(WIN);
  }

  /**
   * Addition of 1 to value.
   * Draw is represented by 1 point so that value should be added to score value.
   * If value after addition would be higher than a limit (7 points) exception will be thrown.
   */
  void addDraw() {
    add(DRAW);
  }

  private void add(int n) {
    if (value + n > LIMIT) {
      throw new VerifyError("Score would be greater than limit(7)!");
    }
    value += n;
  }
}

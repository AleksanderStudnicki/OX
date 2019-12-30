package app.studnicki.ox;

import javax.naming.LimitExceededException;

public class Score {
  public int value;

  /**
   * Addition of 3 to value. Win is represented by 3 points so that value should be added to score value.
   * If value after addition would be higher than a limit (7 points) exception will be thrown.
   */
  public void addWin() {
    if(value + 3 > 7){
      throw new VerifyError("Score would be greater than limit(7)!");
    }
    value += 3;
  }

  /**
   * Addition of 1 to value. Draw is represented by 1 point so that value should be added to score value.
   * If value after addition would be higher than a limit (7 points) exception will be thrown.
   */
  public void addDraw() {
    value += 1;
  }
}

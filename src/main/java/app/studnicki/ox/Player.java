package app.studnicki.ox;

public class Player {

  public String name;
  private final Score score;

  public Player(String name) {
    this.name = name;
    score = new Score();
  }

  /**
   * Add win points to player score. This is just calling the same method on player's score field.
   */
  public void addWin() {
    score.addWin();
  }

  /**
   * Add draw points to player score. This is just calling the same method on player's score field.
   */
  public void addDraw() {
    score.addDraw();
  }

  /**
   * Defines that player has won or not based on minimal requirement for win
   * (5 points: 1 win, 2 draws).
   * @return if player won the game (minimal requirement for win is 5 points)
   */
  public boolean hasWon() {
    return score.value >= 5;
  }


}

package app.studnicki.ox;

class Player {

  final String name;
  final Score score;
  final Sign sign;

  Player(String name, Sign sign) {
    this.name = name;
    score = new Score();
    this.sign = sign;
  }

  /**
   * Add win points to player score. This is just calling the same method on player's score field.
   */
  void addWin() {
    score.addWin();
  }

  /**
   * Add draw points to player score. This is just calling the same method on player's score field.
   */
  void addDraw() {
    score.addDraw();
  }

  /**
   * Defines that player has won or not based on minimal requirement for win
   * (5 points: 1 win, 2 draws).
   *
   * @return if player won the game (minimal requirement for win is 5 points)
   */
  boolean hasWon() {
    return score.value >= 5;
  }

  @Override
  public String toString() {
    return "Player{"
        + "name='" + name + '\''
        + ", score=" + score
        + ", sign=" + sign
        + '}';
  }
}

package app.studnicki.ox;

import java.util.HashMap;
import java.util.Map;

public class Round {
  public final int dimension;
  public Map<Integer, Sign> board = new HashMap<>();

  private final int limit;
  private static final int MININUM_DIMENSION = 3;

  /**
   * Constructor
   *
   * @param dimension Integer value that defines game board size (square: dimension x dimension)
   *                  and range of id fields (0..(dimension^2 - 1)).
   */
  public Round(int dimension) {
    if (dimension < MININUM_DIMENSION) {
      throw new IllegalArgumentException("Board dimension cannot be lesser than 3!");
    }
    this.dimension = dimension;
    limit = dimension * dimension;
  }

  /**
   * Fill the field of game board with O or X value (Sign enum).
   *
   * @param id   Id of field within within range: 0..(dimension^2 - 1).
   * @param sign Naught or cross from Sign enum.
   */
  public void setField(int id, Sign sign) {
    if (sign == null) {
      throw new NullPointerException("Sign cannot be null!");
    }
    if (id >= limit || id < 0) {
      throw new IndexOutOfBoundsException("Id of field does not belong to the board range!");
    }
    if (board.containsKey(id)) {
      throw new IllegalArgumentException("Game board already has a field with that id!");
    }
    board.put(id, sign);
  }
}

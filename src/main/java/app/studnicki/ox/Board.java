package app.studnicki.ox;

import java.util.HashMap;
import java.util.Optional;

/**
 * Representing game board as a class with map holding board state (signs in fields).
 * Class also has dimension and limit (dimension^2 - cause board is a square).
 *
 * @author Aleksander Studnicki
 */
class Board {
  final int limit;
  final int dimension;
  private final HashMap<Integer, Sign> map = new HashMap<>();

  Board(int dimension) {
    this.dimension = dimension;
    this.limit = dimension * dimension;
  }

  /**
   * Puts a sign (naught or cross) into a map with an integer id as a key.
   * @param id Id of field as a an integer.
   * @param sign Sign
   * @throws ExistingFieldException Throws when there is already a field with passed id.
   * @throws NotInBoardRangeException Throws when passed id is not in board range (0, limit - 1).
   */
  void markASign(int id, Sign sign) throws ExistingFieldException, NotInBoardRangeException {
    if (id >= limit || id < 0) {
      throw new NotInBoardRangeException("Id of field does not belong to the board range!");
    }
    if (map.containsKey(id)) {
      throw new ExistingFieldException("Game board already has a field with that id!");
    }
    map.put(id, sign);
  }

  int size() {
    return map.size();
  }

  Optional<Sign> getSignFromField(int id) {
    return Optional.ofNullable(map.get(id));
  }
}

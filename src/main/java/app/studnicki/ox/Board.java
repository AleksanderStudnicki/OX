package app.studnicki.ox;

import java.util.HashMap;
import java.util.Map;

public class Board {
  final int limit;
  final int dimension;
  private final HashMap<Integer, Sign> map = new HashMap<>();

  Board(int dimension) {
    this.dimension = dimension;
    this.limit = dimension * dimension;
  }

  void setField(int id, Sign sign) throws ExistingFieldException, NotInBoardRangeException {
    if (sign == null) {
      throw new NullPointerException("Sign cannot be null!");
    }
    if (id >= limit || id < 0) {
      throw new NotInBoardRangeException("Id of field does not belong to the board range!");
    }
    if (map.containsKey(id)) {
      throw new ExistingFieldException("Game board already has a field with that id!");
    }
    map.put(id, sign);
  }

  Map<Integer, Sign> getMap() {
    return new HashMap<>(map);
  }

  int size() {
    return map.size();
  }

}

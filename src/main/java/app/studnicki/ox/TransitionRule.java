package app.studnicki.ox;

import java.util.Optional;

enum TransitionRule {
  VERTICAL(1, 0),
  HORIZONTAL(0, 1),
  DIAGONAL_UP(-1, 1),
  DIAGONAL_DOWN(1, 1);

  final int row;
  final int column;

  Optional<Integer> nextRight(int id, int dimension) {
    int nextId = id + (row * dimension) + (column);
    if (rightRowDifference(id, nextId, dimension)
        && rightColumnDifference(id, nextId, dimension)) {
      return Optional.of(nextId);
    }
    return Optional.ofNullable(null);
  }

  Optional<Integer> nextLeft(int id, int dimension) {
    int nextId = id - (row * dimension) - (column);
    if (rightRowDifference(id, nextId, dimension)
        && rightColumnDifference(id, nextId, dimension)) {
      return Optional.of(nextId);
    }
    return Optional.ofNullable(null);
  }

  TransitionRule(int row, int column) {
    this.row = row;
    this.column = column;
  }

  private boolean rightRowDifference(int id, int nextId, int dimension) {
    if (row == 0) {
      return true;
    }
    return Math.abs(row(id, dimension) - row(nextId, dimension))
        == Math.abs(row);
  }

  private boolean rightColumnDifference(int id, int nextId, int dimension) {
    if (column == 0) {
      return true;
    }
    return Math.abs(column(id, dimension) - column(nextId, dimension))
        == Math.abs(column);
  }

  private int row(int id, int dimension) {
    return id / dimension;
  }

  private int column(int id, int dimension) {
    return id % dimension;
  }
}

package app.studnicki.ox;

public enum TransitionRule {
  VERTICAL(1, 0),
  HORIZONTAL(0, 1),
  DIAGONAL_UP(-1, 1),
  DIAGONAL_DOWN(1, 1);

  final int row;
  final int column;

  TransitionRule(int row, int column) {
    this.row = row;
    this.column = column;
  }
}

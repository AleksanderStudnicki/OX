package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import static app.studnicki.ox.TransitionRule.*;

class BoardChecker implements PropertyChangeListener {

  private final int winningRule;
  private final Set<TransitionRule> ruleSet = new HashSet<>(
      Set.of(VERTICAL, HORIZONTAL, DIAGONAL_UP, DIAGONAL_DOWN));
  private int turn;

  BoardChecker(int winningRule) {
    this.winningRule = winningRule;
  }

  boolean isWinner(int id, Board board) {
    for (TransitionRule rule : ruleSet) {
      if (isWinner(id, board, rule)) {
        return true;
      }
    }
    return false;
  }

  private boolean isWinner(int id, Board board, TransitionRule rule) {
    Counter counter = new Counter();
    checkStandard(id, rule, board, counter);
    if (counter.value == winningRule) {
      return true;
    }
    checkReversed(id, rule, board, counter);
    return counter.value == winningRule;
  }

  private void checkStandard(int id, TransitionRule rule, Board board, Counter counter) {
    int nextId = id + (rule.row * board.dimension) + (rule.column);

    System.out.println(id + "  |  " + nextId);

    if (rightRowDifference(id, nextId, rule, board)
        && rightColumnDifference(id, nextId, rule, board)) {
      board.getSignFromField(nextId).ifPresent(s -> {
        Sign sign = board.getSignFromField(id).get();
        if (sign.equals(s)) {
          counter.increment();
          if (counter.value != winningRule) {
            checkStandard(nextId, rule, board, counter);
          }
        }
      });
    }
  }

  private void checkReversed(int id, TransitionRule rule, Board board, Counter counter) {
    int nextId = id - (rule.row * board.dimension) - (rule.column);

    if (rightRowDifference(id, nextId, rule, board)
        && rightColumnDifference(id, nextId, rule, board)) {
      board.getSignFromField(nextId).ifPresent(s -> {
        Sign sign = board.getSignFromField(id).get();
        if (sign.equals(s)) {
          counter.increment();
          if (counter.value != winningRule) {
            checkReversed(nextId, rule, board, counter);
          }
        }
      });
    }
  }

  private boolean rightRowDifference(int id, int nextId, TransitionRule rule, Board board) {
    if (rule.row == 0) {
      return true;
    }
    return Math.abs(row(id, board.dimension) - row(nextId, board.dimension)) == Math.abs(rule.row);
  }

  private boolean rightColumnDifference(int id, int nextId, TransitionRule rule, Board board) {
    if (rule.column == 0) {
      return true;
    }
    return Math.abs(column(id, board.dimension) - column(nextId, board.dimension))
        == Math.abs(rule.column);
  }

  private int row(int id, int dimension) {
    return id / dimension;
  }

  private int column(int id, int dimension) {
    return id % dimension;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if(evt.getPropertyName().equals("filledField")){
      checkWinnerIfItIsProperTurn(evt);
    }
  }

  private void checkWinnerIfItIsProperTurn(PropertyChangeEvent evt) {
    turn++;
    if(turn >= winningRule * 2 - 1){
      Board board = (Board) evt.getSource();
      int id = (Integer) evt.getNewValue();
      System.out.println(isWinner(id, board));
    }
  }

  private static class Counter {
    int value = 1;

    void increment() {
      System.out.println(value);
      value++;
    }
  }

}

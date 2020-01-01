package app.studnicki.ox;

import java.util.HashSet;
import java.util.Set;

import static app.studnicki.ox.TransitionRule.*;

class BoardChecker {

  private final int winningRule;
  private final Set<TransitionRule> ruleSet = new HashSet<>(
      Set.of(VERTICAL, HORIZONTAL, DIAGONAL_UP, DIAGONAL_DOWN));

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
    final Counter counter = new Counter();

    Sign sign = board.getSignFromBoard(id).get();
    System.out.println(rule);

    for (int i = 1; ; i++) {
      int nextId = id + (rule.row * board.dimension * i) + (rule.column * i);
      System.out.println(nextId);
      if (!(nextId < board.limit && nextId >= 0)) break;
      if (rightRowDifference(id, nextId, rule, board) && rightColumnDifference(id, nextId, rule, board)) {
        System.out.println("IN");
        if (board.getMap().containsKey(nextId)) {
          System.out.println("OK");
          Sign nextSign = board.getMap().get(nextId);
          if (sign == nextSign) {
            counter.increment();
            if (counter.value == winningRule) {
              return true;
            }
          } else {
            break;
          }
        }
      } else {
        break;
      }
    }

    for (int i = -1; ; i--) {
      int nextId = id + (rule.row * board.dimension * i) + (rule.column * i);
      System.out.println(nextId);
      if (!(nextId < board.limit && nextId >= 0)) break;
      if (rightRowDifference(id, nextId, rule, board) && rightColumnDifference(id, nextId, rule, board)) {
        System.out.println("IN");
        if (board.getMap().containsKey(nextId)) {
          System.out.println("OK");
          Sign nextSign = board.getMap().get(nextId);
          if (sign == nextSign) {
            counter.increment();
            if (counter.value == winningRule) {
              return true;
            }
          } else {
            break;
          }
        }
      } else {
        break;
      }
    }

    return false;
  }

  private boolean rightRowDifference(int id, int nextId, TransitionRule rule, Board board) {
    if (rule.row == 0) {
      return true;
    }
    return Math.abs(row(id, board.dimension) - row(nextId, board.dimension)) == rule.row;
  }

  private boolean rightColumnDifference(int id, int nextId, TransitionRule rule, Board board) {
    if (rule.column == 0) {
      return true;
    }
    return Math.abs(column(id, board.dimension) - column(nextId, board.dimension)) == rule.row;
  }

  private int row(int id, int dimension) {
    return id / dimension;
  }

  private int column(int id, int dimension) {
    return id % dimension;
  }

  private class Counter {
    int value = 1;

    void increment() {
      System.out.println(value);
      value++;
    }
  }
}

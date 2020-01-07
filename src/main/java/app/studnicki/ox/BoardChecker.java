package app.studnicki.ox;

import java.util.EnumSet;
import java.util.Set;

import static app.studnicki.ox.TransitionRule.*;

/**
 * Class for checking a winner of the game after each move on board.
 * Communication with other classes is based on property changes.
 *
 * @author Aleksander Studnicki
 */
class BoardChecker {

  private final int winningRule;
  private final Set<TransitionRule> ruleSet = EnumSet.of(
      VERTICAL, HORIZONTAL, DIAGONAL_DOWN, DIAGONAL_UP);
  private int turn;
  private Board board;
  private boolean ableToCheck = true;

  BoardChecker(int winningRule, Board board) {
    this.winningRule = winningRule;
    this.board = board;
  }

  /**
   * Checks if there is a winner after last field marking.
   *
   * @param id id of recent marked field
   * @return true if is winner and false otherwise
   */
  boolean isWinner(int id) {
    turn++;
    return isWinner(id, board);
  }

  private boolean isWinner(int id, Board board) {
    for (TransitionRule rule : ruleSet) {
      if (isWinner(id, board, rule)) {
        return true;
      }
    }
    if (turn == board.limit) {
      ableToCheck = false;
      return false;
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
    rule.nextRight(id, board.dimension).ifPresent(nextId -> {
      board.getSignFromField(nextId).ifPresent(s -> {
        Sign sign = board.getSignFromField(id).get();
        if (sign.equals(s)) {
          counter.increment();
          if (counter.value != winningRule) {
            checkStandard(nextId, rule, board, counter);
          }
        }
      });
    });
  }

  private void checkReversed(int id, TransitionRule rule, Board board, Counter counter) {
    rule.nextLeft(id, board.dimension).ifPresent(nextId -> {
      board.getSignFromField(nextId).ifPresent(s -> {
        Sign sign = board.getSignFromField(id).get();
        if (sign.equals(s)) {
          counter.increment();
          if (counter.value != winningRule) {
            checkReversed(nextId, rule, board, counter);
          }
        }
      });
    });
  }

  private static class Counter {
    int value = 1;

    void increment() {
      value++;
    }
  }

  /**
   * Returns if amount of turns passed to checker is lesser than board limit.
   * @return return ableToCheck flag
   */
  boolean isAbleToCheck() {
    return ableToCheck;
  }

}

package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumSet;
import java.util.Set;

import static app.studnicki.ox.TransitionRule.*;

/**
 * Class for checking a winner of the game after each move on board.
 * Communication with other classes is based on property changes.
 *
 * @author Aleksander Studnicki
 */
class BoardChecker implements PropertyChangeListener {

  private final int winningRule;
  private final int checkingThreshold;
  private final Set<TransitionRule> ruleSet = EnumSet.of(
      VERTICAL, HORIZONTAL, DIAGONAL_DOWN, DIAGONAL_UP);
  private int turn;

  private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  /**
   * Adds observers for 2 type of events.
   * Playable - when there is possibility to play and no winner.
   * Resolved - when there is a winner or no possibility to play (draw).
   * When resolved then flag is send to observer (true - winner, false - draw).
   *
   * @param listener Object which will be observing GameChecker
   *                 (must implements PropertyChangeListener)
   */
  void addListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener("playable", listener);
    propertyChangeSupport.addPropertyChangeListener("resolved", listener);
  }

  BoardChecker(int winningRule) {
    this.winningRule = winningRule;
    this.checkingThreshold = winningRule * 2 - 1;
  }

  boolean isWinner(int id, Board board) {
    for (TransitionRule rule : ruleSet) {
      if (isWinner(id, board, rule)) {
        turn = 0;
        propertyChangeSupport.firePropertyChange("resolved", null, true);
        return true;
      }
    }
    if (turn == board.limit) {
      turn = 0;
      propertyChangeSupport.firePropertyChange("resolved", null, false);
      return false;
    }
    propertyChangeSupport.firePropertyChange("playable", null, 1);
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

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("filledField")) {
      checkWinnerIfItIsProperTurn(evt);
    }
  }

  private void checkWinnerIfItIsProperTurn(PropertyChangeEvent evt) {
    turn++;
    if (turn >= checkingThreshold) {
      Board board = (Board) evt.getSource();
      int id = (Integer) evt.getNewValue();
      isWinner(id, board);
    } else {
      propertyChangeSupport.firePropertyChange("playable", null, 1);
    }
  }

  private static class Counter {
    int value = 1;

    void increment() {
      value++;
    }
  }

}

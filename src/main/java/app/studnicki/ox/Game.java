package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Game implements PropertyChangeListener {
  final Player player1;
  final Player player2;
  Queue<Player> playersQueue;
  BoardChecker boardChecker;
  int dimension;
  UserInterface userInterface;

  private final List<Round> rounds = new LinkedList<Round>();
  private Round tail;

  private Game(Player player1, Player player2, int winningRule,
               int dimension, UserInterface userInterface) {
    this.player1 = player1;
    this.player2 = player2;
    playersQueue = new LinkedList<>(List.of(player1, player2));
    boardChecker = new BoardChecker(winningRule);
    this.dimension = dimension;
    this.userInterface = userInterface;
    boardChecker.addObserver(this);
  }

  public void start() {
    tail = initRound();
    rounds.add(tail);
    userInterface.board(tail.board);
    play();
  }

  private Round initRound() {
    Round tail = new Round(dimension);

    tail.addObserverForBoardPrinting(userInterface);
    tail.addObserverForCheckingWinner(boardChecker);

    return tail;
  }

  void play() {
    Player player = playersQueue.peek();
    userInterface.nowPlaying(player);
    reverseQueue();
    markASign(player);
  }

  private void markASign(Player player) {
    int id = userInterface.fieldId(dimension * dimension);
    try {
      tail.markASign(id, player.sign);
    } catch (ExistingFieldException ex) {
      userInterface.error(ex.getMessage());
      markASign(player);
    }
  }


  private void reverseQueue() {
    playersQueue.add(playersQueue.poll());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("play")) {
      play();
    }
    if (evt.getPropertyName().equals("resolved")) {
      boolean isWinnerOfRound = (Boolean) evt.getNewValue();

      givePoints(isWinnerOfRound);

      userInterface.announceResultOfRound(player1, player2);

      winnerOfGame().ifPresentOrElse(
          w -> {
            userInterface.announceWinner(w);
            userInterface.waitForAnyAction();
          },
          () -> {
            userInterface.waitForAnyAction();
            this.reverseQueue();
            this.start();
          }
      );
    }
  }

  private Optional<Player> winnerOfGame() {
    return playersQueue.stream().filter(p -> p.score.value >= 5).findFirst();
  }

  private void givePoints(boolean winner) {
    if (winner) {
      reverseQueue();
      playersQueue.peek().addWin();
    } else {
      player1.addDraw();
      player2.addDraw();
    }
  }

  static final class Builder {
    Player player1;
    Player player2;
    int winningRule;
    int dimension;
    UserInterface userInterface;

    Builder player1(Player player1) {
      this.player1 = player1;
      return this;
    }

    Builder player2(Player player2) {
      this.player2 = player2;
      return this;
    }

    Builder winningRule(int winningRule) {
      this.winningRule = winningRule;
      return this;
    }

    Builder dimension(int dimension) {
      this.dimension = dimension;
      return this;
    }

    Builder userInterface(UserInterface userInterface) {
      this.userInterface = userInterface;
      return this;
    }

    Game build() {
      if (winningRule > dimension || winningRule < 3) {
        throw new IllegalArgumentException("Winning rule must be in range 3..dimension - 1");
      }
      return new Game(player1, player2, winningRule, dimension, userInterface);
    }
  }
}

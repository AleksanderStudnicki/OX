package app.studnicki.ox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Class for playing the ox game.
 * It provides all of the game logic when other variables
 * like players, rules, ui are given in constructor.
 * Maximum of 3 rounds can be played.
 *
 * @author Aleksander Studnicki
 */
class Game implements PropertyChangeListener {
  final Player player1;
  final Player player2;
  Queue<Player> playersQueue;
  BoardChecker boardChecker;
  int dimension;
  UserInterface userInterface;

  private final List<Round> rounds = new LinkedList<>();
  private Round tail;

  private Game(Player player1, Player player2, int winningRule,
               int dimension, UserInterface userInterface) {
    this.player1 = player1;
    this.player2 = player2;
    playersQueue = new LinkedList<>(List.of(player1, player2));
    boardChecker = new BoardChecker(winningRule);
    this.dimension = dimension;
    this.userInterface = userInterface;
    boardChecker.addListener(this);
  }

  /**
   * Starts the game by creating a new round, adding it to list of rounds,
   * showing an empty board and running play method.
   */
  void start() {
    tail = initRound();
    rounds.add(tail);
    userInterface.board(tail.board);
    play();
  }

  private Round initRound() {
    Round tail = new Round(dimension);

    tail.addListenerForBoardPrinting(userInterface);
    tail.addListenerForCheckingWinner(boardChecker);

    return tail;
  }

  private void play() {
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

  /**
   * When event occurs then check property name.
   * If play then next turn of the current game.
   * If resolved then show the result and based on that
   * play another round or quit the game and announce winner or draw.
   *
   * @param evt Event from source object. In that case - BoardChecker.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("playable")) {
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
            if (rounds.size() != 3) {
              this.reverseQueue();
              this.start();
            }
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
        throw new IllegalArgumentException(
            Config.INSTANCE.getMessage(MessageKey.WRONG_WINNING_RULE));
      }
      return new Game(player1, player2, winningRule, dimension, userInterface);
    }
  }
}

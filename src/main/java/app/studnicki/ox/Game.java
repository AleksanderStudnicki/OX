package app.studnicki.ox;

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
class Game {
  final Player player1;
  final Player player2;
  Queue<Player> playersQueue;
  int dimension;
  UserInterface userInterface;
  int winningRule;

  private final List<Round> rounds = new LinkedList<>();
  private Round tail;

  private Game(Player player1, Player player2, int winningRule,
               int dimension, UserInterface userInterface) {
    this.player1 = player1;
    this.player2 = player2;
    playersQueue = new LinkedList<>(List.of(player1, player2));
    this.dimension = dimension;
    this.userInterface = userInterface;
    this.winningRule = winningRule;
  }

  /**
   * Starts the game by creating a new round, adding it to list of rounds,
   * showing an empty board and running play method.
   */
  void start() {
    tail = initRound();
    rounds.add(tail);
    userInterface.board(tail.board);
    BoardChecker boardChecker = new BoardChecker(winningRule, tail.board);
    play(boardChecker);
    userInterface.announceResultOfRound(player1, player2);
    winnerOfGame().ifPresentOrElse(
        w -> {
          userInterface.announceWinner(w);
          userInterface.waitForAnyAction();
        },
        () -> {
          if (rounds.size() != 3) {
            userInterface.waitForAnyAction();
            this.reverseQueue();
            this.start();
          } else {
            userInterface.announceDraw();
            userInterface.waitForAnyAction();
          }
        }
    );
  }

  private Round initRound() {
    Round tail = new Round(dimension);
    tail.addListenerForBoardPrinting(userInterface);
    return tail;
  }

  private void play(BoardChecker boardChecker) {
    do {
      Player player = playersQueue.peek();
      userInterface.nowPlaying(player);
      reverseQueue();
      int id = userInterface.fieldId(dimension * dimension);
      try {
        tail.markASign(id, player.sign);
      } catch (ExistingFieldException ex) {
        userInterface.error(ex.getMessage());
        markASign(player);
      }
      if (boardChecker.isWinner(id)) {
        givePoints(true);
        return;
      }
    } while (boardChecker.isAbleToCheck());
    givePoints(false);
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
      if (dimension > Config.MAXIMUM_DIMENSION || dimension < Config.MINIMUM_DIMENSION) {
        throw new IllegalArgumentException(
            Config.INSTANCE.getMessage(MessageKey.WRONG_DIMENSIONS));
      }
      return new Game(player1, player2, winningRule, dimension, userInterface);
    }
  }
}

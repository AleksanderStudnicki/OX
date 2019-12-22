package app.studnicki.ox.game;

import app.studnicki.ox.Sign;
import app.studnicki.ox.ui.UserInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Round {
  UserInterface userInterface;

  Sign[][] board;
  Map<Coordinates, Sign> fields = new HashMap<>();

  Player firstPlayer;
  Player secondPlayer;

  int winningRule;

  Round(UserInterface userInterface, Player firstPlayer, Player secondPlayer, int dimension, int winningRule) {
    this.userInterface = userInterface;

    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;

    this.winningRule = winningRule;

    board = new Sign[dimension][dimension];
    initBoard(board);
  }

  private void initBoard(Sign[][] board) {
    IntStream.range(0, board.length)
        .forEach(i -> {
          Arrays.fill(board[i], Sign.EMPTY);
        });
  }

  void play() {
    userInterface.playersHeader(firstPlayer, secondPlayer);
    userInterface.showBoard(board);
  }
}

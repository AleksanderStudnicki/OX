package app.studnicki.ox.game;

import app.studnicki.ox.Sign;

public class Board {
       final Sign[][] matrix;

  Board(int n) {
    matrix = new Sign[n][n];
  }
}

package app.studnicki.ox.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleGame {

  public static void main(String[] args) {

    int n = 3;
    int winningRule = 3;

    if (args.length >= 2) {
      try {
        int dim = Integer.parseInt(args[0]);
        int wr = Integer.parseInt(args[1]);

        if (dim >= 3) {
          n = dim;
        } else {
          n = 3;
        }
        if (wr <= dim && wr >= 3) {
          winningRule = wr;
        } else {
          winningRule = 3;
        }
      } catch (NumberFormatException ex) {
        System.err.println("Not valid arguments!");
      }
    } else {
      n = 3;
      winningRule = 3;
    }


    Map<Coordinates, Sign> board = new HashMap<>();
    Sign[][] bVis = new Sign[n][n];

    initBoard(bVis);
    showBoard(bVis);


    int steps = 0;

    while (steps < 9) {
      System.out.println("Player 1: ");
      Coordinates coordinates = getCoordinates();

      while (board.containsKey(coordinates)) {
        System.out.println("Player 1: ");
        coordinates = getCoordinates();
      }

      board.put(coordinates, Sign.NAUGHT);
      bVis[coordinates.x][coordinates.y] = Sign.NAUGHT;

      showBoard(bVis);
      steps++;

      if (checkHorizontally(coordinates, board, Sign.NAUGHT, winningRule) == winningRule) {
        System.out.println("PLAYER 1 WIN");
        break;
      }
      if (checkVertically(coordinates, board, Sign.NAUGHT, winningRule) == winningRule) {
        System.out.println("PLAYER 1 WIN");
        break;
      }
      if (checkDiagonallyUp(coordinates, board, Sign.NAUGHT, winningRule) == winningRule) {
        System.out.println("PLAYER 1 WIN");
        break;
      }
      if (checkDiagonallyDown(coordinates, board, Sign.NAUGHT, winningRule) == winningRule) {
        System.out.println("PLAYER 1 WIN");
        break;
      }

      System.out.println("Player 2: ");
      coordinates = getCoordinates();

      while (board.containsKey(coordinates)) {
        System.out.println("Player 2: ");
        coordinates = getCoordinates();
      }

      board.put(coordinates, Sign.CROSS);
      bVis[coordinates.x][coordinates.y] = Sign.CROSS;

      showBoard(bVis);
      steps++;

      if (checkHorizontally(coordinates, board, Sign.CROSS, winningRule) == winningRule) {
        System.out.println("PLAYER 2 WIN");
        break;
      }
      if (checkVertically(coordinates, board, Sign.CROSS, winningRule) == winningRule) {
        System.out.println("PLAYER 2 WIN");
        break;
      }
      if (checkDiagonallyUp(coordinates, board, Sign.CROSS, winningRule) == winningRule) {
        System.out.println("PLAYER 2 WIN");
        break;
      }
      if (checkDiagonallyDown(coordinates, board, Sign.CROSS, winningRule) == winningRule) {
        System.out.println("PLAYER 2 WIN");
        break;
      }
    }
  }

  private static int checkHorizontally(Coordinates coordinates, Map<Coordinates, Sign> board, Sign sign, int winningRule) {
    int counter = 1;


    for (int i = 1; ; i++) {
      Coordinates c = new Coordinates(coordinates.x, coordinates.y + i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    for (int i = -1; ; i--) {
      Coordinates c = new Coordinates(coordinates.x, coordinates.y + i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    return counter;
  }

  private static int checkVertically(Coordinates coordinates, Map<Coordinates, Sign> board, Sign sign, int winningRule) {
    int counter = 1;


    for (int i = 1; ; i++) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    for (int i = -1; ; i--) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    return counter;
  }

  private static int checkDiagonallyUp(Coordinates coordinates, Map<Coordinates, Sign> board, Sign sign, int winningRule) {
    int counter = 1;


    for (int i = 1; ; i++) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y - i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    for (int i = -1; ; i--) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y - i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    return counter;
  }

  private static int checkDiagonallyDown(Coordinates coordinates, Map<Coordinates, Sign> board, Sign sign, int winningRule) {
    int counter = 1;


    for (int i = 1; ; i++) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y + i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    for (int i = -1; ; i--) {
      Coordinates c = new Coordinates(coordinates.x + i, coordinates.y + i);
      if (board.containsKey(c)) {
        Sign s = board.get(c);
        if (s == sign) {
          counter++;
          if (counter == winningRule) {
            return counter;
          }
        } else {
          break;
        }
      } else {
        break;
      }
    }

    return counter;
  }


  private static Coordinates getCoordinates() {
    Scanner scanner = new Scanner(System.in);

    int x = scanner.nextInt();
    int y = scanner.nextInt();

    return new Coordinates(x, y);
  }

  private static void initBoard(Sign[][] board) {
    IntStream.range(0, board.length)
        .forEach(i -> {
          Arrays.fill(board[i], Sign.EMPTY);
        });
  }

  private static void showBoard(Sign[][] board) {
    System.out.print("\033[H\033[2J");
    showBoardHeader(board.length);
    showSeparationLine(board.length);
    IntStream.range(0, board.length)
        .forEach(i -> {
          Sign[] arr = board[i];
          showLine(i, arr);
          showSeparationLine(arr.length);
        });
  }

  private static void showLine(int n, Sign[] arr) {
    System.out.printf("%d |", n);
    Stream.of(arr).forEach(s -> System.out.printf(" %s |", s));
    System.out.println();
  }

  private static void showSeparationLine(int n) {
    System.out.print("  -");
    IntStream.range(0, n).forEach(i -> System.out.print("----"));
    System.out.println();
  }

  private static void showBoardHeader(int n) {
    System.out.print("   ");
    IntStream.range(0, n)
        .forEach(i -> System.out.printf(" %d  ", i));
    System.out.println();
  }
}

package app.studnicki.ox.ui;

import app.studnicki.ox.Sign;
import app.studnicki.ox.config.Config;
import app.studnicki.ox.game.Board;
import app.studnicki.ox.game.Player;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.*;


class ConsoleUserInterface implements UserInterface {
  private ResourceBundle rb = Config.getInstance().getRb();

  @Override
  public void welcome() {
    rb = Config.getInstance().getRb();
    clearScreen();
    out.println(rb.getString("welcome"));
  }

  @Override
  public int menu() {
    showMenu();
    out.println();
    out.print(rb.getString("menu") + " ");
    return readInt();
  }

  @Override
  public int changeLanguage() {
    showChooseLanguageMenu();
    try {
      return new Scanner(System.in).nextInt();
    } catch (InputMismatchException ex) {
      System.err.println(rb.getString("wrongMenuInput"));
      return changeLanguage();
    }
  }

  @Override
  public void playersHeader(Player firstPlayer, Player secondPlayer) {
    out.printf("Player #1 - %s  |  Player #2 - %s%n", firstPlayer, secondPlayer);
  }

  @Override
  public void showBoard(Board board) {
    showBoard(board.matrix);
  }

  @Override
  public String typeFirstName() {
    clearScreen();
    out.println(rb.getString("firstPlayerName"));
    return new Scanner(in).nextLine();
  }

  @Override
  public String typeSecondName() {
    clearScreen();
    out.println(rb.getString("secondPlayerName"));
    return new Scanner(in).nextLine();
  }

  @Override
  public Sign typeSignOfFirstPlayer() {
    clearScreen();

    out.println(rb.getString("signOfFirstPlayer"));

    String line = new Scanner(in).nextLine();

    if (!line.equals("O") && !line.equals("X")) {
      return typeSignOfFirstPlayer();
    }

    if (line.equals("X")) {
      return Sign.X;
    } else {
      return Sign.O;
    }
  }

  private void clearScreen() {
    out.print("\033[H\033[2J");
    out.flush();
  }

  private void showMenu() {
    out.println();
    out.println(rb.getString("playChoice"));
    out.println(rb.getString("languageChoice"));
    out.println();
    out.println(rb.getString("exitChoice"));
  }

  private int readInt() {
    try {
      return new Scanner(in).nextInt();
    } catch (InputMismatchException ex) {
      err.println(rb.getString("wrongMenuInput"));
      return readInt();
    }
  }

  private void showChooseLanguageMenu() {
    clearScreen();
    welcome();
    out.println();
    out.println(rb.getString("english"));
    out.println(rb.getString("polish"));
    out.println(rb.getString("japanese"));
    out.println();
    out.println(rb.getString("back"));
    out.println();
    out.println(rb.getString("changeLanguageMenu"));
  }

  private void showBoard(Sign[][] board) {
    showBoardHeader(board.length);
    showSeparationLine(board.length);
    IntStream.range(0, board.length)
        .forEach(i -> {
          Sign[] arr = board[i];
          showLine(i, arr);
          showSeparationLine(arr.length);
        });
  }

  private void showLine(int n, Sign[] arr) {
    out.printf("%d |", n);
    Stream.of(arr).forEach(s -> out.printf(" %s |", s));
    out.println();
  }

  private void showSeparationLine(int n) {
    out.print("  -");
    IntStream.range(0, n).forEach(i -> out.print("----"));
    out.println();
  }

  private void showBoardHeader(int n) {
    out.print("   ");
    IntStream.range(0, n)
        .forEach(i -> out.printf(" %d  ", i));
    out.println();
  }
}

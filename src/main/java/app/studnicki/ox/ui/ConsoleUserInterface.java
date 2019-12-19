package app.studnicki.ox.ui;

import app.studnicki.ox.config.Config;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

class ConsoleUserInterface implements UserInterface {
  private ResourceBundle rb = Config.getInstance().getRb();

  @Override
  public void welcome() {
    rb = Config.getInstance().getRb();
    clearScreen();
    System.out.println(rb.getString("welcome"));
  }

  @Override
  public int menu() {
    showMenu();
    System.out.println();
    System.out.print(rb.getString("menu") + " ");
    return readInt();
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void showMenu() {
    System.out.println();
    System.out.println(rb.getString("playChoice"));
    System.out.println(rb.getString("languageChoice"));
    System.out.println(rb.getString("exitChoice"));
  }

  private int readInt() {
    try {
      int n = new Scanner(System.in).nextInt();
      return n;
    } catch (InputMismatchException ex) {
      System.err.println(rb.getString("wrongMenuInput"));
      return readInt();
    }
  }
}

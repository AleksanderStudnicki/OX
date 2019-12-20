package app.studnicki.ox.ui;

import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

//

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
  public void playersHeader() {

  }

  @Override
  public void showBoard() {

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
}

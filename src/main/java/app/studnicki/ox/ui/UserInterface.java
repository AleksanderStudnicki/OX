package app.studnicki.ox.ui;

import app.studnicki.ox.Sign;
import app.studnicki.ox.game.Board;

public interface UserInterface {
  void welcome();

  int menu();

  int changeLanguage();

  void playersHeader();

  void showBoard(Board board);

  String typeFirstName();

  String typeSecondName();

  Sign typeSignOfFirstPlayer();

}

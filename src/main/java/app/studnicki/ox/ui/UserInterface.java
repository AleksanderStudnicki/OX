package app.studnicki.ox.ui;

import app.studnicki.ox.Sign;

public interface UserInterface {
  void welcome();

  int menu();

  int changeLanguage();

  void playersHeader();

  void showBoard();

  String typeFirstName();

  String typeSecondName();

  Sign typeSignOfFirstPlayer();
}

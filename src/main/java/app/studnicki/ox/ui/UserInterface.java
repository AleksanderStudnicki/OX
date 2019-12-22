package app.studnicki.ox.ui;

import app.studnicki.ox.Sign;
import app.studnicki.ox.game.Board;
import app.studnicki.ox.game.Player;

public interface UserInterface {
  void welcome();

  int menu();

  int changeLanguage();

  void playersHeader(Player firstPlayer, Player secondPlayer);

  void showBoard(Board board);

  String typeFirstName();

  String typeSecondName();

  Sign typeSignOfFirstPlayer();

}

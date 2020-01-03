package app.studnicki.ox;

enum MessageKey {
  WELCOME("welcome"), BACK("back"), WRONG_MENU_INPUT("wrongMenuInput"),
  FIELD_ID("fieldId"), NOT_IN_RANGE("notInRange"), PRESS_ENTER("pressEnter"),
  WRONG_WINNING_RULE("wrongWinningRule"), YOUR_SCORE("yourScore"),
  YOU_WIN("youWin"), NOW_PLAYING("nowPlaying"), CONGRATULATIONS("congratulations"),
  DRAW("draw"), MENU("menu"), PLAY_CHOICE("playChoice"), LANGUAGE_CHOICE("languageChoice"),
  EXIT_CHOICE("exitChoice"), CHANGE_LANGUAGE_MENU("changeLanguageMenu"), ENGLISH("english"),
  POLISH("polish"), JAPANESE("japanese"), FIRST_PLAYER_NAME("firstPlayerName"),
  SECOND_PLAYER_NAME("secondPlayerName"), SIGN_OF_THE_FIRST_PLAYER("signOfTheFirstPlayer"),
  BOARD_DIMENSIONS("boardDimensions"), WRONG_DIMENSIONS("wrongDimensions"),
  WINNING_RULE_QUESTION("winningRuleQuestion"), WRONG_RULE("wrongRule"),
  STARTING_PLAYER_QUESTION("startingPlayerQuestion");

  private final String value;

  MessageKey(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

package app.studnicki.ox;

enum MessageKey {
  WELCOME("welcome"), BACK("back"), WRONG_MENU_INPUT("wrongMenuInput"),
  FIELD_ID("fieldId"), NOT_IN_RANGE("notInRange"), PRESS_ENTER("pressEnter"),
  WRONG_WINNING_RULE("wrongWinningRule"), YOUR_SCORE("yourScore"),
  YOU_WIN("youWin"), NOW_PLAYING("nowPlaying"), CONGRATULATIONS("congratulations");

  private final String value;

  MessageKey(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

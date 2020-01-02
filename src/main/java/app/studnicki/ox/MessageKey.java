package app.studnicki.ox;

enum MessageKey {
  WELCOME("welcome"), BACK("back"), WRONG_MENU_INPUT("wrongMenuInput"),
  FIELD_ID("fieldId"), NOT_IN_RANGE("notInRange");

  private final String value;

  MessageKey(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

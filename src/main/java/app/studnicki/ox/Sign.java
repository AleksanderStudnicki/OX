package app.studnicki.ox;

public enum Sign {
  O("O"), X("X"), EMPTY(" ");

  private final String value;

  Sign(String value){
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

package app.studnicki.ox;

public enum Sign {
  NAUGHT("O"), CROSS("X");

  private final String value;

  Sign(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}

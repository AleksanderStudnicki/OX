package app.studnicki.ox.ui;

public class UserInterfaceFactory {

  public static UserInterface console() {
    return new ConsoleUserInterface();
  }
}

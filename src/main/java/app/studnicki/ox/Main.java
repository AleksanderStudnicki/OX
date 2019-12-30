package app.studnicki.ox;

class Main {
  public static void main(String[] args) {
    System.out.println("OX GAME");
    ConsoleUserInterface ui = new ConsoleUserInterface();
    Round round = new Round(3);
    round.addObserver(ui);
    round.setField(0, Sign.NAUGHT);
    round.setField(2, Sign.CROSS);
  }
}

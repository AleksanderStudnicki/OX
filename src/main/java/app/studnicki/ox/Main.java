package app.studnicki.ox;

public class Main {
  public static void main(String[] args) {
    System.out.println("OX GAME");
    BoardPrinter boardPrinter = new BoardPrinter();
    Round round = new Round(3);
    round.addObserver(boardPrinter);
    round.setField(0, Sign.NAUGHT);
    round.setField(2, Sign.CROSS);
  }
}

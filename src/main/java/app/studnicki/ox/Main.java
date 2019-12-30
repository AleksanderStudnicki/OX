package app.studnicki.ox;

class Main {
  public static void main(String[] args) {
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);
    Round round = new Round(3);

    round.addObserver(ui);

    ui.welcome();

    ui.board(round.dimension, round.board);

    while (round.board.size() < (round.dimension * round.dimension)) {
      int id = ui.fieldId(round.dimension * round.dimension);
      try {
        round.setField(id, Sign.NAUGHT);
      } catch (IllegalArgumentException ex) {
        ui.error(ex.getMessage());
      }
    }
  }
}

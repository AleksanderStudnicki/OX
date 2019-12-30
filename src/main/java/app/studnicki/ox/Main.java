package app.studnicki.ox;

class Main {
  /**
   * Simple game: just filling the board with naught sign. Board dimension can be customized by args.
   * Error handling is provided.
   * @param args simple main arguments (the first can be treated as a board dimension if is parsable to integer)
   */
  public static void main(String[] args) {
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    int n;

    if (args.length > 0) {
      try {
        n = Integer.parseInt(args[0]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
        n = 3;
      }
    } else {
      n = 3;
    }

    Round round = new Round(n);

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

package app.studnicki.ox;

class Main {
  /**
   * Simple game: just filling the board with naught sign.
   * Board dimension can be customized by args. Error handling is provided.
   * @param args simple main arguments
   *             (the first can be treated as a board dimension if is parsable to integer)
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
    BoardChecker boardChecker = new BoardChecker(3);

    round.addObserver(ui);
    round.addObserver(boardChecker);

    ui.welcome();

    ui.board(round.board);

    boolean naught = true;

    while (round.board.size() < (round.board.limit)) {
      int id = ui.fieldId(round.board.limit);
      try {
        try {
          if(naught){
            round.setField(id, Sign.NAUGHT);
          } else{
            round.setField(id, Sign.CROSS);
          }
          naught = !naught;
        } catch (ExistingFieldException | NotInBoardRangeException e) {
          ui.error(e.getMessage());
        }
      } catch (IllegalArgumentException ex) {
        ui.error(ex.getMessage());
      }

    }
  }
}

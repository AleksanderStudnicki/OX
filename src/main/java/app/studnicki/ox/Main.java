package app.studnicki.ox;

class Main {
  /**
   * Simple game: just filling the board with naught sign.
   * Board dimension can be customized by args. Error handling is provided.
   *
   * @param args simple main arguments
   *             (the first can be treated as a board dimension if is parsable to integer)
   */
  public static void main(String[] args) {
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    int n = 3;
    int winningRule = 3;

    if (args.length == 2) {
      try {
        n = Integer.parseInt(args[0]);
        winningRule = Integer.parseInt(args[1]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
      }
    }

    ui.welcome();

    Player player1 = new Player("Aleksander", Sign.NAUGHT);
    Player player2 = new Player("Czesio", Sign.CROSS);

    Game game = new Game.Builder()
        .player1(player1)
        .player2(player2)
        .winningRule(winningRule)
        .dimension(n)
        .userInterface(ui)
        .build();

    game.start();
  }
}

package app.studnicki.ox;

import java.util.Optional;
/**
 * Main class of the application.
 * Game running if available through the main method.
 * Arguments passed to jar is supported by main method.
 * Because of that there is ability to play after args being passed.
 */
class Main {
  /**
   * Method to run the game.
   * Able to run a game from passed argument
   * @param args simple main arguments
   *             runs the game when arguments are:
   *             [0] - name of the first player
   *             [1] - sign of the first player
   *             [2] - name of the second player
   *             [3] - dimension as an interger
   *             [4] - winning rule as an integer
   */
  public static void main(String[] args) {
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    Optional<Game> argsGame = parseGame(args, ui);

    argsGame.ifPresentOrElse(g -> {
      ui.welcome();
      g.start();
    }, () -> {
      ui.welcome();

      Player player1 = new Player(Config.INSTANCE.getMessage(PLAYER1), Sign.NAUGHT);
      Player player2 = new Player(Config.INSTANCE.getMessage(PLAYER2), Sign.CROSS);

      Game game = new Game.Builder()
          .player1(player1)
          .player2(player2)
          .winningRule(3)
          .dimension(3)
          .userInterface(ui)
          .build();

      game.start();
    });
  }

  private static Optional<Game> parseGame(String[] args, UserInterface ui) {
    if (args.length >= 5) {
      Player player1;
      Player player2;
      int dimension;
      int winningRule;

      if (args[1].equals("O") || args[1].equals("o")) {
        player1 = new Player(args[0], Sign.NAUGHT);
      } else if (args[1].equals("X") || args[1].equals("x")) {
        player1 = new Player(args[0], Sign.CROSS);
      } else {
        ui.error("Not proper sign of first player");
        return Optional.of(null);
      }

      if (player1.sign == Sign.NAUGHT) {
        player2 = new Player(args[2], Sign.CROSS);
      } else {
        player2 = new Player(args[2], Sign.NAUGHT);
      }

      try {
        dimension = Integer.parseInt(args[3]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
        return Optional.of(null);
      }

      try {
        winningRule = Integer.parseInt(args[3]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
        return Optional.of(null);
      }

      return Optional.of(new Game.Builder()
          .player1(player1)
          .player2(player2)
          .dimension(dimension)
          .winningRule(winningRule)
          .userInterface(ui)
          .build());
    }
    return Optional.of(null);
  }

}

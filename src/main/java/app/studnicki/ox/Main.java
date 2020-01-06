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
   *
   * @param args simple main arguments
   *             runs the game when arguments are:
   *             [0] - name of the first player
   *             [1] - sign of the first player
   *             [2] - name of the second player
   *             [3] - dimension as an integer
   *             [4] - winning rule as an integer
   *             [5] - language tag (not required)
   */
  public static void main(String[] args) {
    ConsoleUserInterface ui = new ConsoleUserInterface(System.in);

    changeLanguage(args);
    Optional<Game> argsGame = parseGame(args, ui);

    argsGame.ifPresentOrElse(g -> {
      ui.welcome();
      g.start();
    }, () -> {
        ui.welcome();

        Player player1 = new Player(Config.INSTANCE.getMessage(MessageKey.PLAYER1), Sign.NAUGHT);
        Player player2 = new Player(Config.INSTANCE.getMessage(MessageKey.PLAYER2), Sign.CROSS);

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

  private static void changeLanguage(String[] args) {
    if (args.length > MainNavigation.MINIMUM_ARGS_TO_CHANGE_LANGUAGE.value) {
      Config.INSTANCE.changeLanguage(args[MainNavigation.LANGUAGE_TAG_FIELD.value]);
    }
  }

  private static Optional<Game> parseGame(String[] args, UserInterface ui) {
    if (args.length >= MainNavigation.MINIMUM_ARGS.value) {
      Player player1;
      Player player2;
      int dimension;
      int winningRule;

      if (args[MainNavigation.FIRST_PLAYER_SIGN_FIELD.value].equals("O")
          || args[MainNavigation.FIRST_PLAYER_SIGN_FIELD.value].equals("o")) {
        player1 = new Player(args[MainNavigation.FIRST_PLAYER_NAME_FIELD.value], Sign.NAUGHT);
      } else if (args[MainNavigation.FIRST_PLAYER_SIGN_FIELD.value].equals("X")
              || args[MainNavigation.FIRST_PLAYER_SIGN_FIELD.value].equals("x")) {
        player1 = new Player(args[MainNavigation.FIRST_PLAYER_NAME_FIELD.value], Sign.CROSS);
      } else {
        ui.error("Not proper sign of first player");
        ui.waitForAnyAction();
        return Optional.ofNullable(null);
      }

      if (player1.sign == Sign.NAUGHT) {
        player2 = new Player(args[MainNavigation.SECOND_PLAYER_NAME_FIELD.value], Sign.CROSS);
      } else {
        player2 = new Player(args[MainNavigation.SECOND_PLAYER_NAME_FIELD.value], Sign.NAUGHT);
      }

      try {
        dimension = Integer.parseInt(args[MainNavigation.DIMENSION_FIELD.value]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
        ui.waitForAnyAction();
        return Optional.ofNullable(null);
      }

      try {
        winningRule = Integer.parseInt(args[MainNavigation.WINNING_RULE_FIELD.value]);
      } catch (NumberFormatException ex) {
        ui.error(ex.getMessage());
        ui.waitForAnyAction();
        return Optional.ofNullable(null);
      }

      try {
        return Optional.of(new Game.Builder()
            .player1(player1)
            .player2(player2)
            .dimension(dimension)
            .winningRule(winningRule)
            .userInterface(ui)
            .build());
      } catch (IllegalArgumentException ex) {
        ui.error(ex.getMessage());
        ui.waitForAnyAction();
      }
    }
    return Optional.ofNullable(null);
  }

  private enum MainNavigation {
    FIRST_PLAYER_NAME_FIELD(0),
    FIRST_PLAYER_SIGN_FIELD(1),
    SECOND_PLAYER_NAME_FIELD(2),
    DIMENSION_FIELD(3),
    WINNING_RULE_FIELD(4),
    LANGUAGE_TAG_FIELD(5),
    MINIMUM_ARGS(5),
    MINIMUM_ARGS_TO_CHANGE_LANGUAGE(6);

    final int value;

    MainNavigation(int value) {
      this.value = value;
    }
  }

}

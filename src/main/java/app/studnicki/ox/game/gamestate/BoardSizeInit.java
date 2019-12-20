package app.studnicki.ox.game.gamestate;

class BoardSizeInit implements GameState {
  @Override
  public GameState run() {
    return new WinningRuleInit();
  }
}
